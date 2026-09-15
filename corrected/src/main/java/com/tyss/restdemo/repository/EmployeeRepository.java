package com.tyss.restdemo.repository;

import com.tyss.restdemo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByMobileNo(String mobileNo);

    Optional<Employee> findByEmailOrMobileNo(String email, String mobileNo);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByMobileNoAndIdNot(String mobileNo, Long id);

    Page<Employee> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name,
            String email,
            Pageable pageable);
}


