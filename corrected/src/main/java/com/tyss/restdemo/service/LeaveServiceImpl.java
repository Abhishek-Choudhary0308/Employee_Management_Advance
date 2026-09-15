package com.tyss.restdemo.service;

import com.tyss.restdemo.dto.LeaveRequestDto;
import com.tyss.restdemo.dto.LeaveResponseDto;
import com.tyss.restdemo.entity.Employee;
import com.tyss.restdemo.entity.Leave;
import com.tyss.restdemo.entity.LeaveStatus;
import com.tyss.restdemo.exception.EmployeeNotFoundException;
import com.tyss.restdemo.repository.EmployeeRepository;
import com.tyss.restdemo.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tyss.restdemo.dto.LeavePageResponse;
import org.springframework.data.domain.Page;
import com.tyss.restdemo.dto.LeaveActionDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public LeaveResponseDto applyLeave(
            Long employeeId,
            LeaveRequestDto leaveRequestDto) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with ID: " + employeeId));

        if (leaveRequestDto.getFromDate()
                .isAfter(leaveRequestDto.getToDate())) {

            throw new IllegalArgumentException(
                    "From date cannot be after to date");
        }

        Leave leave = new Leave();

        leave.setFromDate(leaveRequestDto.getFromDate());
        leave.setToDate(leaveRequestDto.getToDate());
        leave.setLeaveType(leaveRequestDto.getLeaveType());
        leave.setReason(leaveRequestDto.getReason());

        // Default status when employee applies
        leave.setStatus("PENDING");

        // Set employee
        leave.setEmployee(employee);

        Leave savedLeave = leaveRepository.save(leave);

        return entityToDto(savedLeave);
    }

//    @Override
//    public List<LeaveResponseDto> getAllLeaves() {
//
//        return leaveRepository.findAll()
//                .stream()
//                .map(this::entityToDto)
//                .toList();
//    }

    @Override
    public LeavePageResponse getAllLeaves(
            String status,
            Pageable pageable) {

        Page<Leave> leavePage;

        if (status == null || status.isBlank()) {

            leavePage = leaveRepository.findAll(pageable);

        } else {

            leavePage = leaveRepository.findByStatusIgnoreCase(
                    status,
                    pageable);
        }

        List<LeaveResponseDto> leaveResponseList =
                leavePage.getContent()
                        .stream()
                        .map(this::entityToDto)
                        .toList();

        LeavePageResponse pageResponse = new LeavePageResponse();

        pageResponse.setLeaveResponseList(leaveResponseList);
        pageResponse.setPage(leavePage.getNumber());
        pageResponse.setSize(leavePage.getSize());
        pageResponse.setTotalElements(leavePage.getTotalElements());
        pageResponse.setTotalPages(leavePage.getTotalPages());

        return pageResponse;
    }

    @Override
    public LeaveResponseDto getLeaveById(Integer leaveId) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave not found with ID: " + leaveId));

        return entityToDto(leave);
    }

    @Override
    public List<LeaveResponseDto> getLeavesByEmployee(Long employeeId) {

        // First verify employee exists
        employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with ID: " + employeeId));

        return leaveRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public LeaveResponseDto updateLeave(
            Integer leaveId,
            LeaveRequestDto leaveRequestDto) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave not found with ID: " + leaveId));

        if (leaveRequestDto.getFromDate()
                .isAfter(leaveRequestDto.getToDate())) {

            throw new IllegalArgumentException(
                    "From date cannot be after to date");
        }

        leave.setFromDate(leaveRequestDto.getFromDate());
        leave.setToDate(leaveRequestDto.getToDate());
        leave.setLeaveType(leaveRequestDto.getLeaveType());
        leave.setReason(leaveRequestDto.getReason());

        Leave updatedLeave = leaveRepository.save(leave);

        return entityToDto(updatedLeave);
    }

    @Override
    public void deleteLeave(Integer leaveId) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave not found with ID: " + leaveId));

        leaveRepository.delete(leave);
    }

    private LeaveResponseDto entityToDto(Leave leave) {

        return LeaveResponseDto.builder()
                .leaveId(leave.getLeaveId())
                .fromDate(leave.getFromDate())
                .toDate(leave.getToDate())
                .leaveType(leave.getLeaveType())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .employeeId(leave.getEmployee().getId())
                .build();
    }

    @Override
    public LeaveResponseDto leaveAction(
            Integer leaveId,
            LeaveActionDto leaveActionDto) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave not found with ID: " + leaveId));

        // Only a PENDING leave can be acted upon
        if (!LeaveStatus.PENDING.name()
                .equalsIgnoreCase(leave.getStatus())) {

            throw new IllegalStateException(
                    "Leave request with ID " + leaveId +
                            " is already " + leave.getStatus() +
                            " and cannot be updated");
        }

        LeaveStatus newStatus = leaveActionDto.getStatus();

        // Applying an action can only move the leave to a terminal state
        if (newStatus == LeaveStatus.PENDING) {
            throw new IllegalArgumentException(
                    "Status must be one of APPROVED, REJECTED or CANCELLED");
        }

        leave.setStatus(newStatus.name());

        Leave updatedLeave = leaveRepository.save(leave);

        return entityToDto(updatedLeave);
    }

}