package com.ishan.petistaan.service;

import com.ishan.petistaan.dto.PetDto;

import java.util.List;

public interface PetService {
    PetDto getPetById(int petId);

    void updatePetName(int petId, String newName);

    void savePet(PetDto pet);

    void deletePet(int petId);

    List<PetDto> getAllPets();
}
