package com.ishan.petistaan.repository.impl;

import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.exception.DuplicatePetIdException;
import com.ishan.petistaan.exception.PetNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PetRepositoryImplTest {

    @Autowired
    PetRepositoryImpl petRepositoryImpl;

    @BeforeEach
    void setUp() {
        petRepositoryImpl.getAllPets().clear(); // Clear the list before each test
    }

    @Test
    void saveTest() throws PetNotFoundException, DuplicatePetIdException {
        PetDto pet = new PetDto();
        pet.setId(1);
        pet.setName("Tommy");

        petRepositoryImpl.save(pet);
        PetDto savedPet = petRepositoryImpl.getPetById(1);
        assertNotNull(savedPet);
        assertEquals(1, savedPet.getId());
        assertEquals("Tommy", savedPet.getName());
    }

    @Test
    void deleteTest() throws DuplicatePetIdException, PetNotFoundException {
        PetDto pet = new PetDto();
        pet.setId(1);
        pet.setName("Tommy");
        petRepositoryImpl.save(pet);

        petRepositoryImpl.delete(1);
        assertThrows(PetNotFoundException.class, () -> petRepositoryImpl.getPetById(1));
    }

    @Test
    void getPetByIdTest() throws PetNotFoundException, DuplicatePetIdException {
        PetDto pet = new PetDto();
        pet.setId(1);
        pet.setName("Tommy");
        petRepositoryImpl.save(pet);

        PetDto retrievedPet = petRepositoryImpl.getPetById(1);
        assertNotNull(retrievedPet);
        assertEquals(1, retrievedPet.getId());
        assertEquals("Tommy", retrievedPet.getName());
    }

    @Test
    void getAllPets() throws DuplicatePetIdException {
        PetDto pet1 = new PetDto();
        pet1.setId(1);
        pet1.setName("Tommy");

        PetDto pet2 = new PetDto();
        pet2.setId(2);
        pet2.setName("Max");

        petRepositoryImpl.save(pet1);
        petRepositoryImpl.save(pet2);

        List<PetDto> pets = petRepositoryImpl.getAllPets();
        assertEquals(2, pets.size());
        assertTrue(pets.contains(pet1));
        assertTrue(pets.contains(pet2));
    }

    @Test
    void updatePetNameTest() throws PetNotFoundException, DuplicatePetIdException {
        PetDto pet = new PetDto();
        pet.setId(1);
        pet.setName("Tommy");
        petRepositoryImpl.save(pet);

        petRepositoryImpl.updatePetName(1, "Max");
        PetDto updatedPet = petRepositoryImpl.getPetById(1);
        assertEquals("Max", updatedPet.getName());
    }

}