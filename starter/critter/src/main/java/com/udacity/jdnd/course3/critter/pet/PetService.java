package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import java.util.List;

public interface PetService {

    Pet savePet(Pet pet);

    Pet getPet (long petId);

    List<Pet> getAllPets();

    List<Pet> getPetsByOwnerId(long ownerId);

}
