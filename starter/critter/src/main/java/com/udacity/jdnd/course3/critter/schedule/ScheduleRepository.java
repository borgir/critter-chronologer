package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.id = :petId")
    List<Schedule> findPetSchedule(@Param("petId") Long petId);

    @Query("SELECT s FROM Schedule s JOIN s.employees e WHERE e.id = :employeeId")
    List<Schedule> findEmployeeSchedule(@Param("employeeId") Long employeeId);

    @Query("SELECT s from Schedule s JOIN s.pets p JOIN p.owner o WHERE o.id = :customerId")
    List<Schedule> findCustomerSchedule(@Param("customerId") Long customerId);

}
