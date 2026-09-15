package com.tyss.restdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeePageResponse {

    private List<EmployeeResponse> employeeReponseList;// need to covert the List of employee entity into List EmployeeResponse

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;
}
