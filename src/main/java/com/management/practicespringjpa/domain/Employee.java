package com.management.practicespringjpa.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    private String empPwd;
    private String empName;
    private String empEmail;
    private int empPosition;
    private String emp_department;
    private String emp_team;
}
