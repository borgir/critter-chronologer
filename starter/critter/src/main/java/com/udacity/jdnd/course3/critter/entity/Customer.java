package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.List;


@Entity(name = "customer")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(
            name = "name",
            length = 50,
            nullable = false
    )
    private String name;


    @Column(
            name = "phone_number",
            length = 20,
            nullable = false
    )
    private String phoneNumber;


    @Column(
            name = "notes",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String notes;


    @OneToMany(
            mappedBy = "owner",
            fetch = FetchType.LAZY,
            targetEntity = Pet.class,
            cascade = CascadeType.ALL
    )
    private List<Pet> pets;




    public Customer(String name, String phoneNumber, String notes, List<Pet> pets) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }




    public Customer() {}




    public long getId() {
        return id;
    }




    public void setId(long id) {
        this.id = id;
    }




    public String getName() {
        return name;
    }




    public void setName(String name) {
        this.name = name;
    }




    public String getPhoneNumber() {
        return phoneNumber;
    }




    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




    public String getNotes() {
        return notes;
    }




    public void setNotes(String notes) {
        this.notes = notes;
    }




    public void addPet(Pet pet) {
        this.pets.add(pet);
    }




    public List<Pet> getPets() {
        return pets;
    }




    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }


}
