package com.tyss.restdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI employeeManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management System API")
                        .version("1.0.0")
                        .description("Spring Boot practical covering REST, JPA, Redis, Batch, Multipart, Transactions and Actuator")
                        .contact(new Contact().name("Training Project")));
    }
}
