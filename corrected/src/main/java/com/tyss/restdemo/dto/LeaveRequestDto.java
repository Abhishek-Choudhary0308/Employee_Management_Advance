package com.tyss.restdemo.dto;

import com.tyss.restdemo.entity.LeaveType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequestDto {
    @NotNull(message = "From date is required")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    private LocalDate toDate;

    @NotNull(message = "Leave type is required")
    private LeaveType leaveType;

    @NotBlank(message = "Reason is required")
    private String reason;

}
