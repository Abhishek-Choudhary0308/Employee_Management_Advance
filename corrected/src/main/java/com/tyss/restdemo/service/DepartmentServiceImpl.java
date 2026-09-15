package com.tyss.restdemo.service;

import com.tyss.restdemo.dto.DepartmentResponseDto;
import com.tyss.restdemo.entity.Department;
import com.tyss.restdemo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDto saveDeartment(String departmentName) {
        Department department = new Department();
        department.setDeptName(departmentName);
        Department dbDepartEntity = departmentRepository.save(department);
        return DepartmentResponseDto.builder()
                .id(dbDepartEntity.getId())
                .deptName(dbDepartEntity.getDeptName())
                .build();
    }
}
