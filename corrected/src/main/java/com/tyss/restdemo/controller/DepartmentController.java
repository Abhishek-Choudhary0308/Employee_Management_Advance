package com.tyss.restdemo.controller;

import com.tyss.restdemo.dto.ResponseDto;
import com.tyss.restdemo.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/department")
    public ResponseDto saveDepartment(@RequestParam String departmentName){
          return ResponseDto.builder()
                  .error(false)
                  .message("Department saved successfully")
                  .data(departmentService.saveDeartment(departmentName))
                  .build();
    }


    public ResponseDto assignDepartment(){
        return ResponseDto.builder().build();
    }
}
