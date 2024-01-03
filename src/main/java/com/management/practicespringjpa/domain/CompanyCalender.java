package com.management.practicespringjpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class CompanyCalender {

    @Id
    private LocalDate date;
    private String schedule;
}
