package com.management.practicespringjpa.controller;

import com.management.practicespringjpa.domain.Employee;
import com.management.practicespringjpa.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class LoginController {

    private final LoginService loginService;

    private final String LOGIN_PAGE = "/LoginPage/LoginPage";
    private final String MAIN_PAGE = "/MainPage/MainPage";

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String home() {
        System.out.println("홈페이지 로드");
        return "LoginPage/LoginPage";
    }

    @RequestMapping(value = "/Login")
    public String login(@RequestParam int inputId, @RequestParam String inputPwd, HttpSession session) {
        System.out.println("로그인 진입");

        System.out.println("ID : " + inputId + " PWD : " + inputPwd);

        boolean isAuthenticated = loginService.loginLogic(inputId, inputPwd);

        if (isAuthenticated) {
            System.out.println("로그인 성공");
            Employee employee = loginService.getEmpIdAndEmpDepartmentAndEmpTeam(inputId);
            session.setAttribute("emp_info", employee);
            System.out.println("emp_id : " + employee.getEmpId() + " emp_department : " + employee.getEmp_department() + " emp_team : " + employee.getEmp_team());
            return MAIN_PAGE;
        } else {
            System.out.println("로그인 실패");
            return LOGIN_PAGE;
        }
    }
}

