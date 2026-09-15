package com.tyss.restdemo.dto;

import com.tyss.restdemo.entity.LeaveStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveActionDto {

    @NotNull(message = "Status is required")
    private LeaveStatus status;

    private String remarks;

}