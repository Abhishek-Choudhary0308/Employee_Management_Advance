package com.tyss.restdemo.dto;

import com.tyss.restdemo.entity.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveResponseDto {

    private Integer leaveId;

    private LocalDate fromDate;

    private LocalDate toDate;

    private LeaveType leaveType;

    private String reason;

    private String status;

    private Long employeeId;

}