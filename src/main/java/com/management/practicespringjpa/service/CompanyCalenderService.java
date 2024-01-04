package com.management.practicespringjpa.service;

import com.management.practicespringjpa.repository.CompanyCalenderRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompanyCalenderService {
    private final CompanyCalenderRepository companyCalenderRepository;

    public CompanyCalenderService(CompanyCalenderRepository companyCalenderRepository) {
        this.companyCalenderRepository = companyCalenderRepository;
    }

    public Map<String, List<String>> getCompanyCalenderList(int year, int month) {
        List<Object[]> scheduleLists = companyCalenderRepository.findScheduleByYearAndMonth(year, month);

        return scheduleLists.stream()
                .collect(Collectors.groupingBy(
                        scheduleList -> (String) scheduleList[1], // Day
                        Collectors.mapping(scheduleList -> (String) scheduleList[0],
                                Collectors.toList()
                        )));
    }
}
