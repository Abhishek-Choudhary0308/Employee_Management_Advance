package com.tyss.restdemo.service;

import com.tyss.restdemo.dto.DepartmentResponseDto;
import com.tyss.restdemo.dto.ResponseDto;

public interface DepartmentService {

    public DepartmentResponseDto saveDeartment(String departmentName);
}
