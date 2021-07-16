package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;


@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;


    @Column(
            name = "name",
            length = 50
    )
    private String name;


    @ElementCollection
    @Column(
            name = "skills"
    )
    private Set<EmployeeSkill> skills;


    @ElementCollection
    @Column(
            name = "days_available"
    )
    private Set<DayOfWeek> daysAvailable;




    public Employee(String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }




    public Employee() {}




    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
    }




    public String getName() {
        return name;
    }




    public void setName(String name) {
        this.name = name;
    }




    public Set<EmployeeSkill> getSkills() {
        return skills;
    }




    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }




    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }




    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }


}
