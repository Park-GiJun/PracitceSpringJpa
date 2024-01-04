package com.management.practicespringjpa.config;

import com.management.practicespringjpa.repository.CompanyCalenderRepository;
import com.management.practicespringjpa.repository.TeamCalenderRepository;
import com.management.practicespringjpa.service.CompanyCalenderService;
import com.management.practicespringjpa.service.TeamCalenderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalenderConfig {

    private final CompanyCalenderRepository companyCalenderRepository;
    private final TeamCalenderRepository teamCalenderRepository;

    public CalenderConfig(CompanyCalenderRepository companyCalenderRepository, TeamCalenderRepository teamCalenderRepository) {
        this.companyCalenderRepository = companyCalenderRepository;
        this.teamCalenderRepository = teamCalenderRepository;
    }

    @Bean
    public CompanyCalenderService companyCalenderService() { return new CompanyCalenderService(companyCalenderRepository); }

    @Bean
    public TeamCalenderService teamCalenderService() { return new TeamCalenderService(teamCalenderRepository); }
}
