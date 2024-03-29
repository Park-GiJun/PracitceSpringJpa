package com.management.practicespringjpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "free_board")
public class FreeBoard {

    @Id
    private int idx;
    private String title;
    private String content;
    private int password;
    private LocalDate date;
}
