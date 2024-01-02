package com.management.practicespringjpa.service;

import com.management.practicespringjpa.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EmployeeRepository employeeRepository;

    public SpringConfig(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Bean
    public LoginService loginService() {
        return new LoginService(employeeRepository);
    }
}
