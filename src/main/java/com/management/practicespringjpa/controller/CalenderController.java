package com.management.practicespringjpa.controller;

import com.google.gson.Gson;
import com.management.practicespringjpa.service.CompanyCalenderService;
import com.management.practicespringjpa.service.TeamCalenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CalenderController {

    private final Gson gson = new Gson();

    private final CompanyCalenderService companyCalenderService;
    private final TeamCalenderService teamCalenderService;


    public CalenderController(CompanyCalenderService companyCalenderService, TeamCalenderService teamCalenderService) {
        this.companyCalenderService = companyCalenderService;
        this.teamCalenderService = teamCalenderService;
    }

    @GetMapping("/CompanySchedule")
    public String CompanySchedule(@RequestParam("selectedYear") int year, @RequestParam("selectedMonth") int month) {
        System.out.println("CompanySchedule : " + year + " " + (month+1));
        Map<String, List<String>> companyScheduleList = companyCalenderService.getCompanyCalenderList(year, month+1);

        String json = gson.toJson(companyScheduleList);

        System.out.println("Check Json : " + json);
        return json;
    }

    @GetMapping("/TeamSchedule")
    public String TeamSchedule() {
        return "TeamSchedule";
    }

    @GetMapping("/EmployeeSchedule")
    public String EmployeeSchedule() {
        return "EmployeeSchedule";
    }

    @PostMapping("/EmployeeSchedule/Save")
    public String EmployeeScheduleSave() {
        return "EmployeeSchedule";
    }
}
