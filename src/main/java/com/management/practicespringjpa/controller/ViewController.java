package com.management.practicespringjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @GetMapping("/Calender")
    public ModelAndView calender() {
        System.out.println("Get Calender Page");
        return new ModelAndView("CalenderPage/CalenderPage");
    }

    @GetMapping("/MyInfo")
    public ModelAndView MyInfo() {
        System.out.println("Get MyInfo Page");
        return new ModelAndView("InfoPage/InfoPage");
    }
}
