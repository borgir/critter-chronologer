package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Employee;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployee(long employeeId);

    void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId);

    List<Employee> getAvailableEmployees(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);

}
