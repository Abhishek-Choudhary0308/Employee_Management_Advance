package com.tyss.restdemo.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Value object used to demonstrate @Embeddable + @Embedded mapping.
 * Its fields are stored in the employees table; no separate table is created.
 */
@Embeddable
@Getter
@Setter
public class EmployeeProfile {
    private String designation;
    private String emergencyContactName;
    private String emergencyContactNo;
}
