package com.tyss.restdemo.service;

import com.tyss.restdemo.dto.EmployeeDocResponse;
import com.tyss.restdemo.dto.EmployeeRequest;
import com.tyss.restdemo.dto.EmployeeResponse;
import com.tyss.restdemo.entity.Employee;
import com.tyss.restdemo.entity.EmployeeDoc;
import com.tyss.restdemo.exception.EmployeeNotFoundException;
import com.tyss.restdemo.repository.EmployeeDocRepository;
import com.tyss.restdemo.repository.EmployeeRepository;
import com.tyss.restdemo.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDocumentService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;
    private final EmployeeDocRepository employeeDocRepository;
    private final FileStorageService fileStorageService;


    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public EmployeeWithDocument saveEmployeeWithDocument(EmployeeRequest request, MultipartFile file) throws IOException {
        EmployeeResponse employee = employeeService.saveEmployee(request);
        if (file == null || file.isEmpty()) {
            return new EmployeeWithDocument(employee, null);
        }
        try {
            return new EmployeeWithDocument(employee, upload(employee.getId(), file));
        } catch (RuntimeException | IOException ex) {
            throw ex;
        }
    }

    public record EmployeeWithDocument(EmployeeResponse employee, EmployeeDocResponse document) {}

    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public EmployeeDocResponse upload(Long employeeId, MultipartFile file) throws IOException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeId));

        FileStorageService.StoredFile stored = fileStorageService.store(file);
        try {
            EmployeeDoc document = new EmployeeDoc();
            document.setEmployee(employee);
            document.setOriginalFileName(stored.originalFileName());
            document.setStoredFileName(stored.storedFileName());
            document.setContentType(stored.contentType());
            document.setFileSize(stored.size());
            document.setStoragePath(stored.path().toString());
            document.setUploadedAt(LocalDateTime.now());

            return toDto(employeeDocRepository.save(document));
        } catch (RuntimeException ex) {
            try { fileStorageService.delete(stored.path()); } catch (IOException ignored) { }
            throw ex;
        }
    }

    @Transactional(readOnly = true)
    public List<EmployeeDocResponse> getDocuments(Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeId));
        return employeeDocRepository.findByEmployeeId(employeeId).stream().map(this::toDto).toList();
    }

    private EmployeeDocResponse toDto(EmployeeDoc doc) {
        return EmployeeDocResponse.builder()
                .id(doc.getId())
                .employeeId(doc.getEmployee().getId())
                .originalFileName(doc.getOriginalFileName())
                .contentType(doc.getContentType())
                .fileSize(doc.getFileSize())
                .uploadedAt(doc.getUploadedAt())
                .build();
    }
}
