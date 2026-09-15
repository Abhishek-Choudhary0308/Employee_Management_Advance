package com.tyss.restdemo.batch;

import com.tyss.restdemo.dto.EmployeeRequest;
import com.tyss.restdemo.entity.Employee;
import com.tyss.restdemo.exception.DuplicateResourceException;
import com.tyss.restdemo.repository.EmployeeRepository;
import com.tyss.restdemo.util.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.support.ListItemReader;
import org.springframework.batch.infrastructure.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class EmployeeBatchConfig {

    /**
     * Creates the Spring Batch Job.
     */
    @Bean
    public Job employeeImportJob(
            JobRepository jobRepository,
            @Qualifier("employeeImportStep") Step employeeImportStep) {

        return new JobBuilder("employeeImportJob", jobRepository)
                .start(employeeImportStep)
                .build();
    }

    /**
     * Creates the Step.
     *
     * Chunk size = 20
     *
     * EmployeeRequest
     *       ↓
     * Reader
     *       ↓
     * Processor
     *       ↓
     * Employee
     *       ↓
     * Writer
     *       ↓
     * MySQL
     */
    @Bean
    public Step employeeImportStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            @Qualifier("employeeRequestReader")
            ItemReader<EmployeeRequest> reader,
            @Qualifier("employeeRequestProcessor")
            ItemProcessor<EmployeeRequest, Employee> processor,
            @Qualifier("employeeItemWriter")
            ItemWriter<Employee> writer) {

        return new StepBuilder("employeeImportStep", jobRepository)
                .<EmployeeRequest, Employee>chunk(20)
                .transactionManager(transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    /**
     * Reads the JSON list stored in the job parameter
     * and converts it into EmployeeRequest objects.
     *
     * IMPORTANT:
     * Jackson 3 is used here.
     */
    @Bean("employeeRequestReader")
    @StepScope
    public ItemReader<EmployeeRequest> employeeRequestReader(
            ObjectMapper objectMapper,
            @Value("#{jobParameters['employeesJson']}")
            String employeesJson) throws Exception {

        log.info("Reading employees from batch job parameter");

        List<EmployeeRequest> employees = objectMapper.readValue(
                employeesJson,
                new TypeReference<List<EmployeeRequest>>() {
                }
        );

        log.info("Total employees received by batch reader: {}",
                employees.size());

        return new ListItemReader<>(employees);
    }

    /**
     * Processes each EmployeeRequest.
     *
     * Performs:
     * 1. Duplicate email check inside current batch
     * 2. Duplicate mobile check inside current batch
     * 3. Duplicate check against database
     * 4. DTO -> Entity conversion
     */
    @Bean("employeeRequestProcessor")
    @StepScope
    public ItemProcessor<EmployeeRequest, Employee> employeeRequestProcessor(
            EmployeeRepository repository) {

        return new ItemProcessor<>() {

            private final Set<String> emails = new HashSet<>();
            private final Set<String> mobiles = new HashSet<>();

            @Override
            public Employee process(EmployeeRequest request) {

                if (request == null) {
                    throw new IllegalArgumentException(
                            "Employee request cannot be null");
                }

                String email = request.getEmail().trim().toLowerCase();
                String mobile = request.getMobileNo().trim();

                /*
                 * Check duplicate email inside the same batch.
                 */
                if (!emails.add(email)) {

                    throw new DuplicateResourceException(
                            "Duplicate email inside batch: "
                                    + request.getEmail());
                }

                /*
                 * Check duplicate mobile inside the same batch.
                 */
                if (!mobiles.add(mobile)) {

                    throw new DuplicateResourceException(
                            "Duplicate mobile inside batch: "
                                    + mobile);
                }

                /*
                 * Check whether employee already exists
                 * in the database.
                 */
                if (repository
                        .findByEmailOrMobileNo(
                                request.getEmail(),
                                request.getMobileNo())
                        .isPresent()) {

                    throw new DuplicateResourceException(
                            "Employee already exists with email/mobile: "
                                    + request.getEmail());
                }

                /*
                 * Convert DTO to Employee entity.
                 */
                return EmployeeMapper.dtoToEntity(request);
            }
        };
    }

    /**
     * Writes processed Employee entities into the database.
     */
    @Bean("employeeItemWriter")
    public ItemWriter<Employee> employeeItemWriter(
            EmployeeRepository repository) {

        return new RepositoryItemWriterBuilder<Employee>()
                .repository(repository)
                .methodName("save")
                .build();
    }
}