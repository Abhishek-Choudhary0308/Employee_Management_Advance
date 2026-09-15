package com.tyss.restdemo.controller;

import com.tyss.restdemo.dto.EmployeePageResponse;
import com.tyss.restdemo.dto.EmployeeV2Request;
import com.tyss.restdemo.dto.ResponseDto;
import com.tyss.restdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/employee")
@RequiredArgsConstructor
public class EmployeeV2Controller {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseDto saveEmployeeV2(@RequestBody EmployeeV2Request employeeV2Request){

        return ResponseDto.builder()
                .error(false)
                .message("Employee saved successfully")
                .data(employeeService.saveEmployeeV2(employeeV2Request))
                .build();
    }

    @GetMapping
    public EmployeePageResponse getAllEmployees(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "id",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable) {

        return employeeService.getEmployeesV2(search, pageable);
    }
}
