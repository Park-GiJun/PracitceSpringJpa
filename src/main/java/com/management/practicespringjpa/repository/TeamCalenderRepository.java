package com.management.practicespringjpa.repository;

import com.management.practicespringjpa.domain.TeamCalender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamCalenderRepository extends JpaRepository<TeamCalender, Long> {

    @Query(value = "SELECT c.schedule, DATE_FORMAT(c.date, '%d') AS Day FROM teamcalender c WHERE YEAR(c.date) = :year AND MONTH(c.date) = :month AND team =:department ORDER BY c.date", nativeQuery = true)
    List<Object[]> findScheduleByYearAndMonth(int year, int month, String department);
}
