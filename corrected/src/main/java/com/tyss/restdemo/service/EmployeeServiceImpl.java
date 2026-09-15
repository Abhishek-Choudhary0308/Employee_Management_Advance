package com.tyss.restdemo.service;

import com.tyss.restdemo.dto.*;
import com.tyss.restdemo.entity.Department;
import com.tyss.restdemo.entity.Employee;
import com.tyss.restdemo.exception.DuplicateResourceException;
import com.tyss.restdemo.exception.EmailNotFoundException;
import com.tyss.restdemo.exception.EmployeeNotFoundException;
import com.tyss.restdemo.repository.DepartmentRepository;
import com.tyss.restdemo.repository.EmployeeRepository;
import com.tyss.restdemo.util.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "employeeList", key = "'all'")
    public List<EmployeeResponse> getAllEmployees() {
        log.info("Fetching all employees from database");
        return employeeRepository.findAll().stream().map(EmployeeMapper::entityToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "employeeById", key = "#id")
    public EmployeeResponse getEmployeeById(Long id) {
        log.info("Fetching employee {} from database", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
        return EmployeeMapper.entityToDto(employee);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "employeeByEmail", key = "#email")
    public EmployeeResponse getEmployeeDetails(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("Employee not found with email: " + email));
        return EmployeeMapper.entityToDto(employee);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    @CacheEvict(cacheNames = {"employeeList"}, allEntries = true)
    public EmployeeResponse saveEmployee(EmployeeRequest request) {
        validateDuplicate(request.getEmail(), request.getMobileNo());
        Employee saved = employeeRepository.save(EmployeeMapper.dtoToEntity(request));
        log.info("Created employee {}", saved.getId());
        return EmployeeMapper.entityToDto(saved);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    @CacheEvict(cacheNames = {"employeeList", "employeeById", "employeeByEmail"}, allEntries = true)
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Cannot update. Employee not found with ID: " + id));

        if (!existing.getEmail().equalsIgnoreCase(request.getEmail())
                && employeeRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new DuplicateResourceException("Another employee is already registered with email '" + request.getEmail() + "'");
        }
        if (!existing.getMobileNo().equalsIgnoreCase(request.getMobileNo())
                && employeeRepository.existsByMobileNoAndIdNot(request.getMobileNo(), id)) {
            throw new DuplicateResourceException("Another employee is already registered with mobile number '" + request.getMobileNo() + "'");
        }

        EmployeeMapper.updateEntityFromDto(existing, request);
        return EmployeeMapper.entityToDto(employeeRepository.save(existing));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    @CacheEvict(cacheNames = {"employeeList", "employeeById", "employeeByEmail"}, allEntries = true)
    public void deleteEmployee(Long id) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Cannot delete. Employee not found with ID: " + id));
        employeeRepository.delete(existing);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    @CacheEvict(cacheNames = {"employeeList", "employeeById", "employeeByEmail"}, allEntries = true)
    public EmployeeV2Response saveEmployeeV2(EmployeeV2Request request) {
        validateDuplicate(request.getEmail(), request.getMobileNo());
        Department department = departmentRepository.findById(request.getDeptId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + request.getDeptId()));
        Employee employee = EmployeeMapper.dtoToEntity(request);
        employee.setDepartment(department);
        return EmployeeMapper.entityToDtoV2(employeeRepository.save(employee));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeePageResponse getEmployeesV2(String search, Pageable pageable) {
        Page<Employee> page = (search == null || search.isBlank())
                ? employeeRepository.findAll(pageable)
                : employeeRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, pageable);

        EmployeePageResponse response = new EmployeePageResponse();
        List<EmployeeResponse> employees = new ArrayList<>();
        for (Employee employee : page.getContent()) {
            employees.add(EmployeeMapper.entityToDto(employee));
        }
        response.setEmployeeReponseList(employees);
        response.setPage(page.getNumber());
        response.setSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        return response;
    }

    private void validateDuplicate(String email, String mobileNo) {
        Optional<Employee> existing = employeeRepository.findByEmailOrMobileNo(email, mobileNo);
        if (existing.isPresent()) {
            Employee matched = existing.get();
            if (matched.getEmail().equalsIgnoreCase(email)) {
                throw new DuplicateResourceException("Employee with email '" + email + "' already exists");
            }
            throw new DuplicateResourceException("Employee with mobile number '" + mobileNo + "' already exists");
        }
    }
}
