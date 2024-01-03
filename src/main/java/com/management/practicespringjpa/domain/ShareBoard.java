package com.management.practicespringjpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Share_board")
public class ShareBoard {

    @Id
    private int idx;
    private String title;
    private String oldfilename;
    private String newfilename;
}
