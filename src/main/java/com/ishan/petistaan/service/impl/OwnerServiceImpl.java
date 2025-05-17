package com.ishan.petistaan.service.impl;

import com.ishan.petistaan.dto.OwnerDto;
import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.exception.DuplicateOwnerIdException;
import com.ishan.petistaan.exception.OwnerNotFoundException;
import com.ishan.petistaan.repository.OwnerRepository;
import com.ishan.petistaan.service.OwnerService;
import com.ishan.petistaan.service.PetService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ishan.petistaan.enums.Gender.F;
import static com.ishan.petistaan.enums.Gender.M;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetService petService;

    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OwnerServiceImpl.class);

    public OwnerServiceImpl(@Autowired OwnerRepository ownerRepository, @Autowired PetService petService) {
        this.ownerRepository = ownerRepository;
        this.petService = petService;
    }

    @Override
    public void saveOwner(OwnerDto ownerDto){
        try {
            ownerRepository.save(ownerDto);
        } catch (DuplicateOwnerIdException e) {
            LOGGER.info(e.getMessage() + " So can't save owner with ID " + ownerDto.getId());
        }
    }

    @Override
    public void updatePetName(int ownerId, String petName){
        OwnerDto ownerDto = getOwnerById(ownerId);
        if (ownerDto != null) {
            PetDto pet = ownerDto.getPetDto();
            if(pet == null ){
                LOGGER.info("No pet found for owner with ID " + ownerId);
            }else{
                petService.updatePetName(pet.getId(), petName);
            }
        } else {
            LOGGER.info("Owner with ID " + ownerId + " not found, so pet name can't be updated");
        }
    }

    @Override
    public void deleteOwner(int ownerId) {
        try {
            ownerRepository.delete(ownerId);
        } catch (OwnerNotFoundException e) {
            LOGGER.info(e.getMessage() + " So can't delete owner with ID " + ownerId);
        }
    }

    @Override
    public OwnerDto getOwnerById(int ownerId) {
        try {
            return ownerRepository.getOwnerById(ownerId);
        } catch (OwnerNotFoundException e) {
            LOGGER.info(e.getMessage() + " So can't get owner with ID " + ownerId);
            return null;
        }
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return ownerRepository.getAllOwners();
    }

    @PostConstruct
    public void init() {
        // Initialize the owner repository with some sample data
        OwnerDto ownerOne = new OwnerDto();
        ownerOne.setId(1);
        ownerOne.setFirstName("John");
        ownerOne.setLastName("Doe");
        ownerOne.setGender(M);
        ownerOne.setCity("Hyderabad");
        ownerOne.setState("Andhra Pradesh");
        ownerOne.setMobileNumber("9009009001");
        ownerOne.setEmailId("john.doe@abhishekvermaa10.com");

        OwnerDto ownerTwo = new OwnerDto();
        ownerTwo.setId(2);
        ownerTwo.setFirstName("Jane");
        ownerTwo.setLastName("Smith");
        ownerTwo.setGender(F);
        ownerTwo.setCity("Vishakhapatnam");
        ownerTwo.setState("Andhra Pradesh");
        ownerTwo.setMobileNumber("9009009002");
        ownerTwo.setEmailId("jane.smith@abhishekvermaa10.com");

        saveOwner(ownerOne);
        saveOwner(ownerTwo);

        List<OwnerDto> ownerList = ownerRepository.getAllOwners();
        List<PetDto> petList = petService.getAllPets();

        ownerList.forEach(owner ->
                petList.stream()
                        .filter(pet -> owner.getId() == pet.getId())
                        .findFirst()
                        .ifPresent(pet -> {
                            owner.setPetDto(pet);
                            pet.setOwnerDto(owner);
                        })
        );

    }
}
