package com.management.practicespringjpa.controller;

import com.management.practicespringjpa.domain.Employee;
import com.management.practicespringjpa.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String home() {
        System.out.println("홈페이지 로드");
        return "LoginPage/LoginPage";
    }

    @RequestMapping(value = "/Login")
    public ModelAndView login(HttpServletRequest request) {
        System.out.println("로그인 진입");
        int inputId = Integer.parseInt(request.getParameter("inputId"));
        String inputPwd = request.getParameter("inputPwd");

        System.out.println("ID : " + inputId + " PWD : " + inputPwd);

        boolean isAuthenticated = loginService.loginLogic(inputId, inputPwd);

        if (isAuthenticated) {
            System.out.println("로그인 성공");
            Employee employee = loginService.getEmpIdAndEmpDepartmentAndEmpTeam(inputId);
            ModelAndView modelAndView = new ModelAndView("/MainPage/MainPage");

            modelAndView.addObject("emp_info", employee);
            System.out.println("emp_id : " + employee.getEmpId() + " emp_department : " + employee.getEmp_department() + " emp_team : " + employee.getEmp_team());
            return modelAndView;
        } else {
            System.out.println("로그인 실패");
            return new ModelAndView("/LoginPage/LoginPage");
        }
    }
}

