package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PetServiceImpl implements PetService {


    PetRepository petRepository;

    CustomerRepository customerRepository;


    public PetServiceImpl(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }




    public Pet savePet(Pet pet) {

        // credits: https://knowledge.udacity.com/questions/496750

        Pet savedPet = petRepository.save(pet);
        Customer customer = savedPet.getCustomer();
        if (customer != null){
            customer.getPets().add(savedPet);
            customerRepository.save(customer);
        }
        return savedPet;

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
