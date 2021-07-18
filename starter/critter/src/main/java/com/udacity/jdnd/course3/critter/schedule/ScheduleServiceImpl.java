package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {


    ScheduleRepository scheduleRepository;


    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }




    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }




    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }




    public List<Schedule> getScheduleByPetId(long petId) {
        return scheduleRepository.findPetSchedule(petId);
    }




    public List<Schedule> getScheduleByEmployeeId(long employeeId) {
        return scheduleRepository.findEmployeeSchedule(employeeId);
    }




    public List<Schedule> getScheduleByOwnerId(long customerId) {
        return scheduleRepository.findCustomerSchedule(customerId);
    }

}
