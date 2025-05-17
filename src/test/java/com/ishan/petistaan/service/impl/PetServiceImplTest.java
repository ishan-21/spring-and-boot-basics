package com.ishan.petistaan.service.impl;

import com.ishan.petistaan.dto.DomesticPetDto;
import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.exception.DuplicatePetIdException;
import com.ishan.petistaan.exception.PetNotFoundException;
import com.ishan.petistaan.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest( classes = { PetServiceImpl.class })
class PetServiceImplTest {
    @Autowired
    private PetServiceImpl petServiceImpl;

    @MockitoBean
    private PetRepository petRepository;

    @Test
    void getPetByIdTest() throws PetNotFoundException {
        PetDto expectedPetDto = new PetDto();
        Mockito.when(petRepository.getPetById(1)).thenReturn(expectedPetDto);
        Assertions.assertEquals(expectedPetDto, petServiceImpl.getPetById(1));
        Mockito.verify(petRepository, Mockito.times(1)).getPetById(1); // this line verifies that the repository method was called exactly once
    }

    @Test
    void getPetByIdTestWhenPetNotThere() {
        try {
            Mockito.when(petRepository.getPetById(3)).thenReturn(null);
            Assertions.assertNull(petServiceImpl.getPetById(3));
            Mockito.verify(petRepository, Mockito.times(1)).getPetById(3);
        } catch (PetNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updatePetNameTest() throws PetNotFoundException {
        petServiceImpl.updatePetName(1, "Tommy");
        Mockito.verify(petRepository, Mockito.times(1)).updatePetName(1, "Tommy");
    }

    @Test
    void savePetTest() throws DuplicatePetIdException {
        PetDto newPetDto = new PetDto();
        petServiceImpl.savePet(newPetDto);
        Mockito.verify(petRepository, Mockito.times(1)).save(newPetDto);
    }

    @Test
    void deletePetTest() throws PetNotFoundException {
        petServiceImpl.deletePet(1);
        Mockito.verify(petRepository, Mockito.times(1)).delete(1);
    }

    @Test
    void getAllPetsTest() {
        List<PetDto> expectedList = new ArrayList<>();
        Mockito.when(petRepository.getAllPets()).thenReturn(expectedList);
        Assertions.assertEquals(expectedList, petServiceImpl.getAllPets());
        Mockito.verify(petRepository, Mockito.times(1)).getAllPets();
    }
}
