package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {


    @Autowired
    PetService petService;


    @Autowired
    CustomerService customerService;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        try {

            Pet pet = convertPetDTOToPet(petDTO);

            Customer owner = customerService.findCustomerById(petDTO.getOwnerId());

            if(owner != null){
                pet.setCustomer(owner);
            }

            Pet updated = petService.savePet(pet);

            return convertPetToPetDTO(updated);

        } catch (Exception e) {

            e.printStackTrace();

            throw new UnsupportedOperationException();

        }

    }




    private PetDTO convertPetToPetDTO(Pet pet) {

        PetDTO petDTO = new PetDTO();

        BeanUtils.copyProperties(pet, petDTO);

        if(pet.getCustomer() != null) {
            petDTO.setOwnerId(pet.getCustomer().getId());
        }

        return petDTO;
    }




    private Pet convertPetDTOToPet(PetDTO petDTO) {

        Pet pet = new Pet();

        BeanUtils.copyProperties(petDTO, pet);

        return pet;

    }




    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        Pet pet = petService.getPet(petId);

        return convertPetToPetDTO(pet);
    }




    @GetMapping
    public List<PetDTO> getPets() {

        List<PetDTO> petDTOList = new ArrayList<>();

        List<Pet> petList = petService.getAllPets();

        for(Pet pet : petList) {
            PetDTO petDTO = convertPetToPetDTO(pet);
            petDTOList.add(petDTO);
        }

        return petDTOList;
    }




    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        List<PetDTO> petDTOList = new ArrayList<>();

        List<Pet> petList = petService.getPetsByOwnerId(ownerId);

        for(Pet p : petList){
            PetDTO dto = convertPetToPetDTO(p);
            petDTOList.add(dto);
        }

        return petDTOList;
    }

}
