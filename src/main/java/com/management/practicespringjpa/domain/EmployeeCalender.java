package com.management.practicespringjpa.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "employeecalender")
public class EmployeeCalender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "emp_Id")
    private int empId;
}
