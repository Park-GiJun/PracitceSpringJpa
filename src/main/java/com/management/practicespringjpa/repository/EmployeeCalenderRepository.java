package com.management.practicespringjpa.repository;

import com.management.practicespringjpa.domain.EmployeeCalender;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeCalenderRepository extends JpaRepository<EmployeeCalender, Long> {

    @Query(value = "SELECT e.schedule, DATE_FORMAT(e.date, '%d') AS Day FROM EmployeeCalender e WHERE YEAR(e.date) = :year AND MONTH(e.date) = :month AND e.empId= :emp_id order by e.date")
    List<Object[]> findByYearAndMonth(int emp_id, int year, int month);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employeecalender WHERE emp_id = :emp_id AND date = :date AND schedule = :schedule", nativeQuery = true)
    void deleteByEmpIdAndDateAndSchedule(@Param("emp_id") int emp_id, @Param("date") LocalDate date, @Param("schedule") String schedule);

}
