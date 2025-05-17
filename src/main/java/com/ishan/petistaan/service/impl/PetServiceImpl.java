package com.ishan.petistaan.service.impl;

import com.ishan.petistaan.dto.DomesticPetDto;
import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.dto.WildPetDto;
import com.ishan.petistaan.exception.DuplicatePetIdException;
import com.ishan.petistaan.exception.PetNotFoundException;
import com.ishan.petistaan.repository.PetRepository;
import com.ishan.petistaan.service.PetService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.ishan.petistaan.enums.Gender.F;
import static com.ishan.petistaan.enums.Gender.M;
import static com.ishan.petistaan.enums.PetType.CAT;
import static com.ishan.petistaan.enums.PetType.DOG;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PetServiceImpl.class);
    public PetServiceImpl(@Autowired PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public PetDto getPetById(int petId){
        try{
            return petRepository.getPetById(petId);
        }
        catch (PetNotFoundException e){
            LOGGER.info( e.getMessage() + " So can't get pet with ID " + petId);
            return null;
        }
    }

    @Override
    public void updatePetName(int petId, String newName){
        try{
            petRepository.updatePetName(petId, newName);
        } catch (PetNotFoundException e) {
            LOGGER.info(e.getMessage() + " So name can't be updated");
        }
    }

    @Override
    public void savePet(PetDto pet) {
        try{
            petRepository.save(pet);
        } catch (DuplicatePetIdException e) {
            LOGGER.info(e.getMessage() + " So can't save pet with ID " + pet.getId());
        }
    }

    @Override
    public void deletePet(int petId) {
        try{
            petRepository.delete(petId);
        } catch (PetNotFoundException e) {
            LOGGER.info(e.getMessage() + " So can't delete pet with ID " + petId);
        }
    }

    @Override
    public List<PetDto> getAllPets() {
        return petRepository.getAllPets();
    }

    @PostConstruct
    public void init() {
        DomesticPetDto domesticPetDTO = new DomesticPetDto();
        domesticPetDTO.setId(1);
        domesticPetDTO.setName("Max");
        domesticPetDTO.setGender(M);
        domesticPetDTO.setPetType(DOG);
        domesticPetDTO.setBirthDate(LocalDate.of(2018, 7, 26));

        WildPetDto wildPetDTO = new WildPetDto();
        wildPetDTO.setId(2);
        wildPetDTO.setName("Fluffy");
        wildPetDTO.setGender(F);
        wildPetDTO.setPetType(CAT);
        wildPetDTO.setBirthPlace("Jim Corbett National Park");

        savePet(domesticPetDTO);
        savePet(wildPetDTO);
    }

}
