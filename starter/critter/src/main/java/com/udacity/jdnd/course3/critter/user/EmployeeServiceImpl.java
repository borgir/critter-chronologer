package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.*;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;




    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }




    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }




    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }




    public Employee getEmployee(long employeeId) {
        Employee employee;
        Optional<Employee> _employee = employeeRepository.findById(employeeId);
        if(_employee.isPresent()){
            employee = _employee.get();
            return employee;
        } else {
            return null;
        }
    }




    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee;
        Optional<Employee> _employee = employeeRepository.findById(employeeId);
        if(_employee.isPresent()){
            employee = _employee.get();
            employee.setDaysAvailable(daysAvailable);
            employeeRepository.save(employee);
        }
    }




    public List<Employee> getAvailableEmployees(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        List<Employee> employees = new ArrayList<>();
        List<Employee> employeesAvailable = employeeRepository.findAllEmployeesByDaysAvailable(dayOfWeek);
        for (Employee e : employeesAvailable){
            if(e.getSkills().containsAll(skills)){
                employees.add(e);
            }
        }
        return employees;
    }

}
