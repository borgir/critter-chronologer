package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e.* FROM employee AS e INNER JOIN employee_days_available AS eda ON eda.days_available = :day GROUP BY e.id")
    List<Employee> findAllEmployeesByDaysAvailable(@Param("day") DayOfWeek day);

}
