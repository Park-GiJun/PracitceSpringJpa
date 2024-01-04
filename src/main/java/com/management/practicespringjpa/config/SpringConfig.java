package com.management.practicespringjpa.config;

import com.management.practicespringjpa.repository.EmployeeRepository;
import com.management.practicespringjpa.repository.FreeBoardRepository;
import com.management.practicespringjpa.repository.ShareBoardRepository;
import com.management.practicespringjpa.service.FreeBoardService;
import com.management.practicespringjpa.service.LoginService;
import com.management.practicespringjpa.service.ShareBoardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EmployeeRepository employeeRepository;
    private final ShareBoardRepository shareBoardRepository;
    private final FreeBoardRepository freeBoardRepository;


    public SpringConfig(EmployeeRepository employeeRepository, ShareBoardRepository shareBoardRepository, FreeBoardRepository freeBoardRepository) {
        this.employeeRepository = employeeRepository;
        this.shareBoardRepository = shareBoardRepository;
        this.freeBoardRepository = freeBoardRepository;
    }

    @Bean
    public LoginService loginService() {
        return new LoginService(employeeRepository);
    }

    @Bean
    public ShareBoardService shareBoardService() { return new ShareBoardService(shareBoardRepository); }

    @Bean
    public FreeBoardService freeBoardService() { return new FreeBoardService(freeBoardRepository); }


}
