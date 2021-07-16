package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s.* FROM schedule s INNER JOIN schedule_pets AS sp on sp.schedule_id = s.id WHERE sp.pets_id = :petId")
    List<Schedule> findScheduleForPet(@Param("petId") Long petId);

    @Query("SELECT s.* FROM schedule s INNER JOIN schedule_employees AS se on se.schedule_id = s.id WHERE se.employees_id = :employeeId")
    List<Schedule> findScheduleForEmployee(@Param("employeeId") Long employeeId);

    @Query("SELECT s.* " +
            "FROM schedule s " +
            "INNER JOIN schedule_pets AS sp on sp.schedule_id = s.id " +
            "INNER JOIN customer_pets AS cp ON cp.pets_id = sp.pets_id" +
            "INNER JOIN customer AS c ON c.id = cp.customer_id" +
            "WHERE c.id = :customerId")
    List<Schedule> findScheduleForCustomer(@Param("customerId") Long customerId);

}
