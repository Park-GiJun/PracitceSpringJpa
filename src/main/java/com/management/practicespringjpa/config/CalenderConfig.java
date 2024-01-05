package com.management.practicespringjpa.config;

import com.management.practicespringjpa.repository.CompanyCalenderRepository;
import com.management.practicespringjpa.repository.EmployeeCalenderRepository;
import com.management.practicespringjpa.repository.TeamCalenderRepository;
import com.management.practicespringjpa.service.CompanyCalenderService;
import com.management.practicespringjpa.service.EmployeeCalenderService;
import com.management.practicespringjpa.service.TeamCalenderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalenderConfig {

    private final CompanyCalenderRepository companyCalenderRepository;
    private final TeamCalenderRepository teamCalenderRepository;
    private final EmployeeCalenderRepository employeeCalenderRepository;

    public CalenderConfig(CompanyCalenderRepository companyCalenderRepository, TeamCalenderRepository teamCalenderRepository, EmployeeCalenderRepository employeeCalenderRepository) {
        this.companyCalenderRepository = companyCalenderRepository;
        this.teamCalenderRepository = teamCalenderRepository;
        this.employeeCalenderRepository = employeeCalenderRepository;
    }

    @Bean
    public CompanyCalenderService companyCalenderService() { return new CompanyCalenderService(companyCalenderRepository); }

    @Bean
    public TeamCalenderService teamCalenderService() { return new TeamCalenderService(teamCalenderRepository); }

    @Bean
    public EmployeeCalenderService employeeCalenderService() { return new EmployeeCalenderService(employeeCalenderRepository); }
}
