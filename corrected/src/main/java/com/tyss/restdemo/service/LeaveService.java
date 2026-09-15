package com.tyss.restdemo.service;

import com.tyss.restdemo.dto.LeaveActionDto;
import com.tyss.restdemo.dto.LeavePageResponse;
import com.tyss.restdemo.dto.LeaveRequestDto;
import com.tyss.restdemo.dto.LeaveResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LeaveService {

    LeaveResponseDto applyLeave(Long employeeId, LeaveRequestDto leaveRequestDto);

   // List<LeaveResponseDto> getAllLeaves();
   LeavePageResponse getAllLeaves(String status, Pageable pageable);

    LeaveResponseDto getLeaveById(Integer leaveId);

    List<LeaveResponseDto> getLeavesByEmployee(Long employeeId);

    LeaveResponseDto updateLeave(Integer leaveId, LeaveRequestDto leaveRequestDto);

    void deleteLeave(Integer leaveId);

    LeaveResponseDto leaveAction(Integer leaveId, LeaveActionDto leaveActionDto);

}