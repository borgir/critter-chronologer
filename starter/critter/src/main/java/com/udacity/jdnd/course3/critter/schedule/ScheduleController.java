package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
@Transactional
public class ScheduleController {


    @Autowired
    ScheduleService scheduleService;


    @Autowired
    PetService petService;


    @Autowired
    EmployeeService employeeService;


    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToSchedule(scheduleDTO);
        schedule = scheduleService.saveSchedule(schedule);
        return convertScheduleToScheduleDTO(schedule);
    }




    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Pet> petList = petService.getAllPets();
        List<Employee> employeeList = employeeService.getAllEmployees();

        List<Long> pets = new ArrayList<>();
        if(petList.size() > 0){
            for(Pet _pet : petList){
                pets.add(_pet.getId());
            }
        }

        List<Long> employees = new ArrayList<>();
        if(employeeList.size() > 0){
            for(Employee _employee : employeeList) {
                employees.add(_employee.getId());
            }
        }
        scheduleDTO.setPetIds(pets);
        scheduleDTO.setEmployeeIds(employees);

        return scheduleDTO;
    }




    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {

        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        List<Long> pets = scheduleDTO.getPetIds();
        List<Long> employees = scheduleDTO.getEmployeeIds();

        List<Pet> petList = new ArrayList<>();
        if(pets.size() > 0){
            for(Long id : pets) {
                Pet pet = petService.getPet(id);
                petList.add(pet);
            }
        }

        List<Employee> employeeList = new ArrayList<>();
        if(pets.size() > 0) {
            for(Long id : employees) {
                Employee employee = employeeService.getEmployee(id);
                employeeList.add(employee);
            }
        }

        schedule.setEmployees(employeeList);
        schedule.setPets(petList);

        return schedule;
    }




    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        if(scheduleList.size() > 0) {
            for(Schedule schedule : scheduleList){
                ScheduleDTO scheduleDTO = convertScheduleToScheduleDTO(schedule);
                scheduleDTOList.add(scheduleDTO);
            }
        }
        return scheduleDTOList;
    }




    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByPetId(petId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        if(scheduleList.size() > 0) {
            for(Schedule schedule : scheduleList){
                ScheduleDTO scheduleDTO = convertScheduleToScheduleDTO(schedule);
                scheduleDTOList.add(scheduleDTO);
            }
        }
        return scheduleDTOList;
    }




    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByEmployeeId(employeeId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        if(scheduleList.size() > 0) {
            for(Schedule schedule : scheduleList) {
                ScheduleDTO scheduleDTO = convertScheduleToScheduleDTO(schedule);
                scheduleDTOList.add(scheduleDTO);
            }
        }
        return scheduleDTOList;
    }




    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByOwnerId(customerId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        if(scheduleList.size() > 0) {
            for(Schedule schedule : scheduleList){
                ScheduleDTO scheduleDTO = convertScheduleToScheduleDTO(schedule);
                scheduleDTOList.add(scheduleDTO);
            }
        }
        return scheduleDTOList;
    }

}
