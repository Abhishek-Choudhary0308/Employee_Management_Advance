package com.tyss.restdemo.service;

import com.tyss.restdemo.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse getEmployeeDetails(String email);

    EmployeeResponse saveEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest);

    void deleteEmployee(Long id);

    EmployeeV2Response saveEmployeeV2(EmployeeV2Request employeeV2Request);

    EmployeePageResponse getEmployeesV2(String search,
                                        Pageable pageable);
}

