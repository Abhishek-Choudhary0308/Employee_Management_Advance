package com.tyss.restdemo.controller;

import com.tyss.restdemo.dto.LeaveActionDto;
import com.tyss.restdemo.dto.LeaveRequestDto;
import com.tyss.restdemo.dto.LeaveResponseDto;
import com.tyss.restdemo.dto.ResponseDto;
import com.tyss.restdemo.service.LeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leaves") //("/api/v1/leave")
public class LeaveController {

    private final LeaveService leaveService;

    // Apply leave for an employee
    @PostMapping("/employee/{employeeId}")
    public ResponseDto applyLeave(
            @PathVariable Long employeeId,
            @Valid @RequestBody LeaveRequestDto leaveRequestDto) {

        return ResponseDto.builder()
                .error(false)
                .message("Leave applied successfully")
                .data(leaveService.applyLeave(
                        employeeId,
                        leaveRequestDto))
                .build();
    }

    // Get all leaves
//    @GetMapping
//    public ResponseDto getAllLeaves() {
//
//        return ResponseDto.builder()
//                .error(false)
//                .message("Leaves fetched successfully")
//                .data(leaveService.getAllLeaves())
//                .build();
//    }

    @GetMapping
    public ResponseDto getAllLeaves(
            @RequestParam(required = false) String status,
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "leaveId",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable) {

        return ResponseDto.builder()
                .error(false)
                .message("Leaves fetched successfully")
                .data(leaveService.getAllLeaves(status, pageable))
                .build();
    }

    // Get leave by ID
    @GetMapping("/{leaveId}")
    public ResponseDto getLeaveById(
            @PathVariable Integer leaveId) {

        return ResponseDto.builder()
                .error(false)
                .message("Leave fetched successfully")
                .data(leaveService.getLeaveById(leaveId))
                .build();
    }

    // Get all leaves of an employee
    @GetMapping("/employee/{employeeId}")
    public ResponseDto getLeavesByEmployee(
            @PathVariable Long employeeId) {

        return ResponseDto.builder()
                .error(false)
                .message("Employee leaves fetched successfully")
                .data(leaveService.getLeavesByEmployee(employeeId))
                .build();
    }

    // Update leave
    @PutMapping("/{leaveId}")
    public ResponseDto updateLeave(
            @PathVariable Integer leaveId,
            @Valid @RequestBody LeaveRequestDto leaveRequestDto) {

        return ResponseDto.builder()
                .error(false)
                .message("Leave updated successfully")
                .data(leaveService.updateLeave(
                        leaveId,
                        leaveRequestDto))
                .build();
    }

    // Delete leave
    @DeleteMapping("/{leaveId}")
    public ResponseDto deleteLeave(
            @PathVariable Integer leaveId) {

        leaveService.deleteLeave(leaveId);

        return ResponseDto.builder()
                .error(false)
                .message("Leave deleted successfully")
                .data(null)
                .build();
    }

    // Approve / Reject / Cancel a leave request (status-only update)
    @PatchMapping("/{leaveId}/action")
    public ResponseDto leaveAction(
            @PathVariable Integer leaveId,
            @Valid @RequestBody LeaveActionDto leaveActionDto) {

        return ResponseDto.builder()
                .error(false)
                .message("Leave status updated successfully")
                .data(leaveService.leaveAction(
                        leaveId,
                        leaveActionDto))
                .build();
    }

}