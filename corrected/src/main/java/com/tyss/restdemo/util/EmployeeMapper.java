package com.tyss.restdemo.util;

import com.tyss.restdemo.dto.*;
import com.tyss.restdemo.entity.Address;
import com.tyss.restdemo.entity.Department;
import com.tyss.restdemo.entity.Employee;
import com.tyss.restdemo.entity.EmployeeProfile;

/**
 * Utility class for explicit manual DTO ↔ Entity conversion.
 * 
 * Architectural Note on DTO Mapping Boundary:
 * Manual mapping decouples the public API contract (DTOs) from the internal persistence model (Entities).
 * It prevents exposing sensitive persistence fields (like internal IDs, passwords, or audit flags)
 * and guards against mass-assignment / over-posting vulnerabilities.
 */
public class EmployeeMapper {

    private EmployeeMapper() {
        // Private constructor to prevent instantiation of utility class
    }

    public static EmployeeResponse entityToDto(Employee employeeEntity) {
        if (employeeEntity == null) {
            return null;
        }
        Address address = employeeEntity.getAddress();
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setStreet(address.getStreet());
        addressDto.setZipCode(address.getZipCode());

        return EmployeeResponse.builder()
                .id(employeeEntity.getId())
                .name(employeeEntity.getName())
                .email(employeeEntity.getEmail())
                .mobileNo(employeeEntity.getMobileNo())
                .addressDto(addressDto)
                .profile(employeeEntity.getProfile() == null ? null : EmployeeProfileDto.builder()
                        .designation(employeeEntity.getProfile().getDesignation())
                        .emergencyContactName(employeeEntity.getProfile().getEmergencyContactName())
                        .emergencyContactNo(employeeEntity.getProfile().getEmergencyContactNo())
                        .build())
                .build();
    }
    public static EmployeeV2Response entityToDtoV2(Employee employeeEntity) {
        if (employeeEntity == null) {
            return null;
        }
        Address address = employeeEntity.getAddress();
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setStreet(address.getStreet());
        addressDto.setZipCode(address.getZipCode());

        Department department = employeeEntity.getDepartment();



        return EmployeeV2Response.builder()
                .id(employeeEntity.getId())
                .name(employeeEntity.getName())
                .email(employeeEntity.getEmail())
                .mobileNo(employeeEntity.getMobileNo())
                .addressDto(addressDto)
                .departmentResponseDto(DepartmentResponseDto.builder()
                        .id(department.getId())
                        .deptName(department.getDeptName())
                        .build())
                .build();
    }

    public static Employee dtoToEntity(EmployeeRequest employeeRequest) {
        if (employeeRequest == null) {
            return null;
        }
        Employee employeeEntity = new Employee();

        //Address dto to entity conversion
        Address addressEntity = new Address();
        AddressDto addressDto = employeeRequest.getAddressDto();
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setState(addressDto.getState());
        addressEntity.setZipCode(addressDto.getZipCode());


        employeeEntity.setName(employeeRequest.getName());
        employeeEntity.setEmail(employeeRequest.getEmail());
        employeeEntity.setMobileNo(employeeRequest.getMobileNo());
        employeeEntity.setPassword(employeeRequest.getPassword());
        employeeEntity.setAddress(addressEntity);

        if (employeeRequest.getProfile() != null) {
            EmployeeProfile profile = new EmployeeProfile();
            profile.setDesignation(employeeRequest.getProfile().getDesignation());
            profile.setEmergencyContactName(employeeRequest.getProfile().getEmergencyContactName());
            profile.setEmergencyContactNo(employeeRequest.getProfile().getEmergencyContactNo());
            employeeEntity.setProfile(profile);
        }
        return employeeEntity;
    }
    public static Employee dtoToEntity(EmployeeV2Request employeeRequest) {
        if (employeeRequest == null) {
            return null;
        }
        Employee employeeEntity = new Employee();

        //Address dto to entity conversion
        Address addressEntity = new Address();
        AddressDto addressDto = employeeRequest.getAddressDto();
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setState(addressDto.getState());
        addressEntity.setZipCode(addressDto.getZipCode());


        employeeEntity.setName(employeeRequest.getName());
        employeeEntity.setEmail(employeeRequest.getEmail());
        employeeEntity.setMobileNo(employeeRequest.getMobileNo());
        employeeEntity.setPassword(employeeRequest.getPassword());
        employeeEntity.setAddress(addressEntity);
        return employeeEntity;
    }

    public static void updateEntityFromDto(Employee employeeEntity, EmployeeRequest employeeRequest) {
        if (employeeEntity == null || employeeRequest == null) {
            return;
        }
        employeeEntity.setName(employeeRequest.getName());
        employeeEntity.setEmail(employeeRequest.getEmail());
        employeeEntity.setMobileNo(employeeRequest.getMobileNo());
        if (employeeRequest.getPassword() != null && !employeeRequest.getPassword().isBlank()) {
            employeeEntity.setPassword(employeeRequest.getPassword());
        }
    }
}

