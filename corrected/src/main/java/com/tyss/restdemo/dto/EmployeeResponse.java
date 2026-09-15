package com.tyss.restdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private Long id;

    private String name;

    private String email;

    private String mobileNo;

    private AddressDto addressDto;

    private EmployeeProfileDto profile;

}

