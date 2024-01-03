package com.management.practicespringjpa.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FreeBoardData {

    private int idx;
    private String title;
    private String content;
    private int password;
    private LocalDate date;
}
