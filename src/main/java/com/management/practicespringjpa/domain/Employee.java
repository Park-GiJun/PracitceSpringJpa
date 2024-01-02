package com.management.practicespringjpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_Id")
    private int empId;

    @Column(name = "emp_Pwd")
    private String empPwd;

    @Column(name = "emp_Name")
    private String empName;

    @Column(name = "emp_Email")
    private String empEmail;

    @Column(name = "emp_Grade")
    private int empGrade;
}
