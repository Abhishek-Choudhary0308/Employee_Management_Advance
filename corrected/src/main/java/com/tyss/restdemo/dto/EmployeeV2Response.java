package com.tyss.restdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeV2Response {

    private Long id;

    private String name;

    private String email;

    private String mobileNo;

    private AddressDto addressDto;

    private DepartmentResponseDto departmentResponseDto;

}
