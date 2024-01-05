package com.management.practicespringjpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class employeeCalenderRequest {

	@JsonProperty("emp_Id")
	private int emp_id;

	@JsonProperty("date")
	private LocalDate date;

	@JsonProperty("text")
	private String schedule;
}
