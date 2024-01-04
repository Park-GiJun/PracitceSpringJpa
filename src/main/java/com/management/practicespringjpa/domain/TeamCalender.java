package com.management.practicespringjpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "teamCalender")
public class TeamCalender {
    @Id
    private String schedule;
    private LocalDate date;
    private String team;
}
