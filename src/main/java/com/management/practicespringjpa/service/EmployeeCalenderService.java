package com.management.practicespringjpa.service;

import com.management.practicespringjpa.domain.EmployeeCalender;
import com.management.practicespringjpa.repository.EmployeeCalenderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeCalenderService {
	private final EmployeeCalenderRepository employeeCalenderRepository;

	public EmployeeCalenderService (EmployeeCalenderRepository employeeCalenderRepository) {
		this.employeeCalenderRepository = employeeCalenderRepository;
	}

	public Map<String, List<String>> getEmployeeSchedule (int emp_id, int year, int month) {
		List<Object[]> scheduleLists = employeeCalenderRepository.findByYearAndMonth (emp_id, year, month);

		return scheduleLists.stream ()
				.collect (Collectors.groupingBy (
						scheduleList -> (String) scheduleList[1], // Day
						Collectors.mapping (scheduleList -> (String) scheduleList[0],
						                    Collectors.toList ()
						)));
	}

	public void saveEmployeeSchedule (int emp_id, LocalDate date, String schedule) {
		EmployeeCalender employeeCalender = new EmployeeCalender ();
        employeeCalender.setEmpId (emp_id);
        employeeCalender.setDate (date);
        employeeCalender.setSchedule (schedule);
        employeeCalenderRepository.save (employeeCalender);
	}

	public void deleteEmployeeSchedule (int emp_id, LocalDate date, String schedule) {
        employeeCalenderRepository.deleteByEmpIdAndDateAndSchedule (emp_id, date, schedule);
	}
}
