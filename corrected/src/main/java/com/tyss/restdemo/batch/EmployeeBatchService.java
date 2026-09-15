package com.tyss.restdemo.batch;

import com.tyss.restdemo.dto.EmployeeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeBatchService {

    private final JobLauncher jobLauncher;

    private final Job employeeImportJob;

    private final ObjectMapper objectMapper;

    public JobExecution createEmployeesInBatch(
            List<EmployeeRequest> employees) throws Exception {

        // Validate request
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one employee is required"
            );
        }

        // Convert employee list to JSON
        String employeesJson =
                objectMapper.writeValueAsString(employees);

        // Generate unique ID for every batch request
        String requestId =
                UUID.randomUUID().toString();

        // Create JobParameters
        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addString("requestId", requestId)
                        .addString("employeesJson", employeesJson)
                        .toJobParameters();

        log.info(
                "Starting employee batch job. Request ID: {}",
                requestId
        );

        // Launch Spring Batch job
        JobExecution jobExecution =
                jobLauncher.run(
                        employeeImportJob,
                        jobParameters
                );

        log.info(
                "Employee batch job started. Execution ID: {}",
                jobExecution.getId()
        );

        return jobExecution;
    }
}