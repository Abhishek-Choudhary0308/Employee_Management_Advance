package com.tyss.restdemo.controller;

import com.tyss.restdemo.dto.EmployeeRequest;
import com.tyss.restdemo.dto.ResponseDto;
import com.tyss.restdemo.service.EmployeeDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v3/employee")
@RequiredArgsConstructor
public class EmployeeV3Controller {

    private final EmployeeDocumentService employeeDocumentService;

    /** Creates an employee and optionally stores a profile document in one multipart request. */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> saveEmployeeV3(
            @Valid @RequestPart("employee") EmployeeRequest employeeRequest,
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        EmployeeDocumentService.EmployeeWithDocument result =
                employeeDocumentService.saveEmployeeWithDocument(employeeRequest, file);

        return ResponseEntity.status(201).body(ResponseDto.builder()
                .error(false)
                .message("Employee and document saved successfully")
                .data(result)
                .build());
    }

    @PostMapping(value = "/{employeeId}/document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadDocument(
            @PathVariable Long employeeId,
            @RequestPart("file") MultipartFile file) throws Exception {
        return ResponseDto.builder()
                .error(false)
                .message("Document uploaded successfully")
                .data(employeeDocumentService.upload(employeeId, file))
                .build();
    }

    @GetMapping("/{employeeId}/document")
    public ResponseDto getDocuments(@PathVariable Long employeeId) {
        return ResponseDto.builder()
                .error(false)
                .message("Documents fetched successfully")
                .data(employeeDocumentService.getDocuments(employeeId))
                .build();
    }

}
