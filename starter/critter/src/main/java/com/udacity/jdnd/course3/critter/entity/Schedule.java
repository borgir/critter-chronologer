package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
public class Schedule {


    @Id
    @GeneratedValue
    private long id;


    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees;


    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets;


    @Column(
            name = "date",
            nullable = false
    )
    private LocalDate date;


    @Column(
            name = "activities",
            nullable = false
    )
    @ElementCollection
    private Set<EmployeeSkill> activities;




    public Schedule(LocalDate date, List<Employee> employees, List<Pet> pets, Set<EmployeeSkill> activities) {
        this.date = date;
        this.employees = employees;
        this.pets = pets;
        this.activities = activities;
    }




    public Schedule() {}




    public long getId() {
        return id;
    }




    public void setId(long id) {
        this.id = id;
    }




    public List<Employee> getEmployees() {
        return employees;
    }




    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }




    public List<Pet> getPets() {
        return pets;
    }




    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }




    public LocalDate getDate() {
        return date;
    }




    public void setDate(LocalDate date) {
        this.date = date;
    }




    public Set<EmployeeSkill> getActivities() {
        return activities;
    }




    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }


}
