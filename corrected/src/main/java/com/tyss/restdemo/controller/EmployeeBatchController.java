package com.tyss.restdemo.controller;

import com.tyss.restdemo.batch.EmployeeBatchService;
import com.tyss.restdemo.dto.EmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
public class EmployeeBatchController {

    private final EmployeeBatchService employeeBatchService;

    /**
     * Create multiple employees using Spring Batch.
     *
     * POST /api/batch/employees
     */
    @PostMapping("/employees")
    public ResponseEntity<String> createMultipleEmployees(
            @RequestBody List<EmployeeRequest> employees) {

        try {

            // Check whether request body is empty
            if (employees == null || employees.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Employee list cannot be empty");
            }

            // Start Spring Batch job
            JobExecution jobExecution =
                    employeeBatchService.createEmployeesInBatch(employees);

            // Return job execution information
            return ResponseEntity.ok(
                    "Batch job started successfully. " +
                            "Job Execution ID: " + jobExecution.getId()
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        } catch (Exception e) {

            return ResponseEntity.internalServerError()
                    .body("Batch job failed: " + e.getMessage());
        }
    }
}