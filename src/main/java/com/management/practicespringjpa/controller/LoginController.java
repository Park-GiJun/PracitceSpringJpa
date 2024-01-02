package com.management.practicespringjpa.controller;

import com.management.practicespringjpa.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String home() {
        return "LoginPage/LoginPage";
    }

    @PostMapping("/Login")
    public String login(HttpServletRequest request) {
        int inputId = Integer.parseInt(request.getParameter("inputId"));
        String inputPwd = request.getParameter("inputPwd");

        boolean isAuthenticated = loginService.loginLogic(inputId, inputPwd);

        if (isAuthenticated) {
            return "/MainPage/MainPage";
        } else {
            return "redirect:/LoginPage/LoginPage";
        }
    }
}

