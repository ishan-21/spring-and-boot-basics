package com.ishan.petistaan.service;

import com.ishan.petistaan.dto.PetDto;

import java.util.List;

public interface PetService {
    PetDto findPet(int petId);

    void updatePetName(int petId, String newName);

    Double findAverageAgeOfPets();
}
