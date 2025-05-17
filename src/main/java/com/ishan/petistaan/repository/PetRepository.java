package com.ishan.petistaan.repository;

import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.exception.DuplicatePetIdException;
import com.ishan.petistaan.exception.PetNotFoundException;

import java.util.List;

public interface PetRepository {

    PetDto getPetById(int petId) throws PetNotFoundException;

    void updatePetName(int petId, String petName) throws PetNotFoundException;

    void save(PetDto pet) throws DuplicatePetIdException;

    void delete(int petId) throws PetNotFoundException;

    List<PetDto> getAllPets();
}
