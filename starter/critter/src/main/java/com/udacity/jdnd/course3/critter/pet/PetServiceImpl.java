package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PetServiceImpl implements PetService {


    PetRepository petRepository;


    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }




    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }




    public Pet getPet(long petId) {
        Pet pet;
        Optional<Pet> _pet = petRepository.findById(petId);
        if(_pet.isPresent()){
            pet = _pet.get();
            return pet;
        } else {
            return null;
        }
    }




    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }




    public List<Pet> getPetsByOwnerId(long ownerId) {
        return petRepository.findAllPetsByCustomerId(ownerId);
    }

}
