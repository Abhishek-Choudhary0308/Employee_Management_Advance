package com.tyss.restdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LeavePageResponse {

    private List<LeaveResponseDto> leaveResponseList;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;
}