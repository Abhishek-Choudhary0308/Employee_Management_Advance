package com.tyss.restdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDocResponse {

    private Long id;

    private Long employeeId;

    private String originalFileName;

    private String storedFileName;

    private String contentType;

    private Long fileSize;

    private String storagePath;

    private LocalDateTime uploadedAt;
}