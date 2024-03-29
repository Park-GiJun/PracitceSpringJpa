package com.management.practicespringjpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "companyCalender")
public class CompanyCalender {

    @Id
    private LocalDate date;
    private String schedule;
}
