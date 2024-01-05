package com.management.practicespringjpa.controller;

import com.google.gson.Gson;
import com.management.practicespringjpa.dto.employeeCalenderRequest;
import com.management.practicespringjpa.service.CompanyCalenderService;
import com.management.practicespringjpa.service.EmployeeCalenderService;
import com.management.practicespringjpa.service.TeamCalenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CalenderController {

	private final Gson gson = new Gson ();

	private final CompanyCalenderService companyCalenderService;
	private final TeamCalenderService teamCalenderService;
	private final EmployeeCalenderService employeeCalenderService;


	public CalenderController (CompanyCalenderService companyCalenderService, TeamCalenderService teamCalenderService, EmployeeCalenderService employeeCalenderService) {
		this.companyCalenderService = companyCalenderService;
		this.teamCalenderService = teamCalenderService;
		this.employeeCalenderService = employeeCalenderService;
	}

	@GetMapping("/CompanySchedule")
	public String CompanySchedule (@RequestParam("selectedYear") int year, @RequestParam("selectedMonth") int month) {
		System.out.println ("CompanySchedule : " + year + " " + (month + 1));
		Map<String, List<String>> companyScheduleList = companyCalenderService.getCompanyCalenderList (year, month + 1);
		String json = gson.toJson (companyScheduleList);
		System.out.println ("Check Json : " + json);
		return json;
	}

	@GetMapping("/TeamSchedule")
	public String TeamSchedule (@RequestParam("selectedYear") int year, @RequestParam("selectedMonth") int month, @RequestParam("department") String department) {
		System.out.println ("CompanySchedule : " + year + " " + (month + 1));
		Map<String, List<String>> teamScheduleList = teamCalenderService.getTeamCalenderList (year, month + 1, department);
		String json = gson.toJson (teamScheduleList);
		System.out.println ("check Json : " + json);
		return json;
	}

	@GetMapping("/EmployeeSchedule")
	public String EmployeeSchedule (@RequestParam("selectedYear") int year, @RequestParam("selectedMonth") int month, @RequestParam("emp_id") int emp_id) {
		System.out.println ("EmployeeSchedule : " + year + " " + (month + 1) + " " + emp_id);
		Map<String, List<String>> employeeScheduleList = employeeCalenderService.getEmployeeSchedule (emp_id, year, month + 1);
		String json = gson.toJson (employeeScheduleList);
		System.out.println ("check Json : " + json);
		return json;
	}

	@PostMapping("/EmployeeSchedule/Save")
	public String EmployeeScheduleSave (@RequestBody employeeCalenderRequest employeeCalenderRequest) {
		System.out.println ("EmployeeScheduleSave : " + employeeCalenderRequest);
		employeeCalenderService.saveEmployeeSchedule(employeeCalenderRequest.getEmp_id (), employeeCalenderRequest.getDate (), employeeCalenderRequest.getSchedule ());
		return "success";
	}

	@PostMapping("/EmployeeSchedule/Delete")
	public String EmployeeScheduleDelete (@RequestBody employeeCalenderRequest employeeCalenderRequest) {
		System.out.println ("EmployeeScheduleDelete : " + employeeCalenderRequest);
		employeeCalenderService.deleteEmployeeSchedule(employeeCalenderRequest.getEmp_id (), employeeCalenderRequest.getDate (), employeeCalenderRequest.getSchedule ());
		return "success";
	}
}
