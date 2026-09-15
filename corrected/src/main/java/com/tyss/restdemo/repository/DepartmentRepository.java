package com.tyss.restdemo.repository;

import com.tyss.restdemo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer>{

}
