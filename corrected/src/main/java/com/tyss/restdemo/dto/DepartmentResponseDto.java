package com.tyss.restdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentResponseDto {

    private Integer id;

    private String deptName;
}
