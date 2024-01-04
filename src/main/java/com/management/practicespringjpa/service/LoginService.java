package com.management.practicespringjpa.service;

import com.management.practicespringjpa.domain.Employee;
import com.management.practicespringjpa.repository.EmployeeRepository;

public class LoginService {

    private final EmployeeRepository employeeRepository;

    public LoginService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean loginLogic(int empId, String empPwd) {
        Employee employee = employeeRepository.findByEmpId(empId);

        if (employee != null) {
            return employee.getEmpPwd().equals(empPwd);
        }
        return false;
    }

    public Employee getEmpIdAndEmpDepartmentAndEmpTeam(int empId) {
        return employeeRepository.findByEmpId(empId);
    }
}
