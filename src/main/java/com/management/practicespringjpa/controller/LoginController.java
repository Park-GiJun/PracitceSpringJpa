package com.management.practicespringjpa.controller;

import com.management.practicespringjpa.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(HttpServletRequest request) {
        System.out.println("로그인 진입");
        int inputId = Integer.parseInt(request.getParameter("inputId"));
        String inputPwd = request.getParameter("inputPwd");

        System.out.println("ID : " + inputId + " PWD : " + inputPwd);

        boolean isAuthenticated = loginService.loginLogic(inputId, inputPwd);

        if (isAuthenticated) {
            System.out.println("로그인 성공");
            return "/MainPage/MainPage";
        } else {
            System.out.println("로그인 실패");
            return "/LoginPage/LoginPage";
        }
    }
}

