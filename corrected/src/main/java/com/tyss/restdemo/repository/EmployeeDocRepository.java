package com.tyss.restdemo.repository;

import com.tyss.restdemo.entity.EmployeeDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDocRepository
        extends JpaRepository<EmployeeDoc, Long> {

    List<EmployeeDoc> findByEmployeeId(Long employeeId);

    Optional<EmployeeDoc> findFirstByEmployeeIdOrderByIdDesc(
            Long employeeId
    );
}