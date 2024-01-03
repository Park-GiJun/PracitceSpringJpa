package com.management.practicespringjpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Share_board")
public class ShareBoard {

    @Id
    private int idx;
    private String title;
    private String oldfilename;
    private String newfilename;
}
