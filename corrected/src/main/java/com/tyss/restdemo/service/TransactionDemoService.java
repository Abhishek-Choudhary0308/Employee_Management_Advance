package com.tyss.restdemo.service;

import com.tyss.restdemo.entity.Department;
import com.tyss.restdemo.exception.BusinessWarningException;
import com.tyss.restdemo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDemoService {

    private final DepartmentRepository departmentRepository;

    /** Demonstrates rollbackFor: the insert is rolled back because the exception is thrown. */
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public void rollbackDemo(String name) {
        Department department = new Department();
        department.setDeptName(name);
        departmentRepository.save(department);
        throw new IllegalStateException("Intentional exception: transaction should roll back");
    }

    /** Demonstrates noRollbackFor: the insert is committed even though the warning is thrown. */
    @Transactional(noRollbackFor = BusinessWarningException.class, timeout = 30)
    public void noRollbackDemo(String name) {
        Department department = new Department();
        department.setDeptName(name);
        departmentRepository.save(department);
        throw new BusinessWarningException("Intentional warning: transaction should still commit");
    }

    /**
     * Demonstrates a read-only transaction. This method performs only SELECT operations.
     * readOnly=true tells the transaction manager/JPA provider that no entity changes are expected.
     */
    @Transactional(readOnly = true)
    public List<Department> getDepartmentsReadOnly() {
        return departmentRepository.findAll();
    }
}
