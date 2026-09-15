package com.tyss.restdemo.repository;

import com.tyss.restdemo.entity.Leave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findByEmployeeId(Long employeeId);
    Page<Leave> findByStatusIgnoreCase(
            String status,
            Pageable pageable);
}