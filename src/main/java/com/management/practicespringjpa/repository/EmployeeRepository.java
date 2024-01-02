package com.management.practicespringjpa.repository;

import com.management.practicespringjpa.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpId(int empId);
}
