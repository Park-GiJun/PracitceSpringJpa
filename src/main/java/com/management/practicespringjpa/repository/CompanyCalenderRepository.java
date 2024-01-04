package com.management.practicespringjpa.repository;

import com.management.practicespringjpa.domain.CompanyCalender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyCalenderRepository extends JpaRepository<CompanyCalender, Long> {

    @Query(value = "SELECT c.schedule, DATE_FORMAT(c.date, '%d') AS Day FROM companyCalender c WHERE YEAR(c.date) = :year AND MONTH(c.date) = :month ORDER BY c.date", nativeQuery = true)
    List<Object[]> findScheduleByYearAndMonth(int year, int month);

}
