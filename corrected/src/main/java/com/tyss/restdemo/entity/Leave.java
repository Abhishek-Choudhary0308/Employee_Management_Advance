package com.tyss.restdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "leaves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveId;

    private LocalDate fromDate;

    private LocalDate toDate;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    private String reason;

    private String status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


}
