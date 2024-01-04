package com.management.practicespringjpa.service;

import com.management.practicespringjpa.repository.TeamCalenderRepository;

public class TeamCalenderService {
    private final TeamCalenderRepository teamCalenderRepository;

    public TeamCalenderService(TeamCalenderRepository teamCalenderRepository) {
        this.teamCalenderRepository = teamCalenderRepository;
    }
}
