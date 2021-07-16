package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Pet {


    @Id
    @GeneratedValue
    private Long id;


    @Column(
            name = "type",
            nullable = false
    )
    private PetType type;


    @Column(
            length = 50,
            name = "name",
            nullable = false
    )
    private String name;


    @Column(
            name = "birth_date",
            nullable = false
    )
    private LocalDate birthDate;


    @Column(
            name = "notes",
            nullable = true
    )
    private String notes;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private Customer owner;




    public Pet(PetType type, String name, LocalDate birthDate, String notes, Customer owner) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
        this.owner = owner;
    }




    public Pet() {}




    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
    }




    public PetType getType() {
        return type;
    }




    public void setType(PetType type) {
        this.type = type;
    }




    public String getName() {
        return name;
    }




    public void setName(String name) {
        this.name = name;
    }




    public LocalDate getBirthDate() {
        return birthDate;
    }




    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }




    public String getNotes() {
        return notes;
    }




    public void setNotes(String notes) {
        this.notes = notes;
    }




    public Customer getCustomer() {
        return owner;
    }




    public void setCustomer(Customer owner) {
        this.owner = owner;
    }


}
