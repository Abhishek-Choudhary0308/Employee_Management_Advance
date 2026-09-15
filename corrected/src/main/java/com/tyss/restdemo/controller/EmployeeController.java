package com.tyss.restdemo.controller;

import com.tyss.restdemo.dto.EmployeeRequest;
import com.tyss.restdemo.dto.EmployeeResponse;
import com.tyss.restdemo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller exposing Employee management resources under /api/employees.
 * <p>
 * Architectural Note on Resource Identification vs Business Attributes:
 * - Technical/Resource Identifier: 'id' (Long PK) is used in REST URIs (/api/employees/{id}) as the surrogate identity.
 * - Business Attributes: 'email' and 'mobileNo' are business-unique attributes validated at application and database layers.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable String email) {
        EmployeeResponse employee = employeeService.getEmployeeDetails(email);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse savedEmployee = employeeService.saveEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse updatedEmployee = employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

