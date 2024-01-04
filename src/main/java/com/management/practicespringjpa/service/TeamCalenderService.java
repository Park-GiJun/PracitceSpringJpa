package com.management.practicespringjpa.service;

import com.management.practicespringjpa.repository.TeamCalenderRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeamCalenderService {
    private final TeamCalenderRepository teamCalenderRepository;

    public TeamCalenderService(TeamCalenderRepository teamCalenderRepository) {
        this.teamCalenderRepository = teamCalenderRepository;
    }

    public Map<String, List<String>> getTeamCalenderList(int year, int month, String department) {
        List<Object[]> scheduleLists = teamCalenderRepository.findScheduleByYearAndMonth(year, month, department);

        return scheduleLists.stream()
                .collect(Collectors.groupingBy(
                        scheduleList -> (String) scheduleList[1],
                        Collectors.mapping(scheduleList -> (String) scheduleList[0],
                                Collectors.toList()
                        )));
    }
}
