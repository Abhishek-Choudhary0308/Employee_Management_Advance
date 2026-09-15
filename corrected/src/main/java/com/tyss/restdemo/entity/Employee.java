package com.tyss.restdemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "mobile_no", nullable = false, unique = true, length = 15)
    private String mobileNo;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // @Embedded stores these fields directly in the employees table.
    @Embedded
    private EmployeeProfile profile;

    // Leave balances - initialized for every newly created employee.
    @Column(name = "sick_leave_balance", nullable = false, columnDefinition = "int default 10")
    @Builder.Default
    private Integer sickLeaveBalance = 10;

    @Column(name = "casual_leave_balance", nullable = false, columnDefinition = "int default 10")
    @Builder.Default
    private Integer casualLeaveBalance = 10;

    @Column(name = "earned_leave_balance", nullable = false, columnDefinition = "int default 5")
    @Builder.Default
    private Integer earnedLeaveBalance = 5;

    @Column(name = "unpaid_leave_balance", nullable = false, columnDefinition = "int default 5")
    @Builder.Default
    private Integer unpaidLeaveBalance = 5;

    // orphanRemoval=true demonstrates automatic deletion of child leaves removed from this collection.
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Leave> leaves = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<EmployeeDoc> documents = new ArrayList<>();
}
