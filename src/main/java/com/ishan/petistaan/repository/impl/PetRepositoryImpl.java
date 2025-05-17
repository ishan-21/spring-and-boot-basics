package com.ishan.petistaan.repository.impl;

import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.exception.DuplicatePetIdException;
import com.ishan.petistaan.exception.PetNotFoundException;
import com.ishan.petistaan.repository.PetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepositoryImpl implements PetRepository {

    private final List<PetDto> petList;
    private final String petNotFoundMessage;
    private final String duplicatePetIdMessage;

    public PetRepositoryImpl( @Value("${pet.not.found}")String petNotFoundMessage,
                              @Value("${pet.already.exists}") String duplicatePetIdMessage) {
        this.petNotFoundMessage = petNotFoundMessage;
        this.duplicatePetIdMessage = duplicatePetIdMessage;
        this.petList = new ArrayList<>();
    }

    @Override
    public PetDto getPetById(int petId) throws PetNotFoundException {
        return petList.stream()
                .filter(pet -> pet.getId() == petId)
                .findFirst()
                .orElseThrow(() -> new PetNotFoundException(String.format(petNotFoundMessage, petId)));
    }

    @Override
    public void updatePetName(int petId, String petName) throws PetNotFoundException {
        PetDto pet = getPetById(petId);
        pet.setName(petName);
    }


    @Override
    public void save(PetDto pet) throws DuplicatePetIdException {
        if (petList.stream().anyMatch(p -> p.getId() == pet.getId())) {
            throw new DuplicatePetIdException(String.format(duplicatePetIdMessage, pet.getId()));
        }
        petList.add(pet);
    }

    @Override
    public void delete(int petId) throws PetNotFoundException {
        PetDto pet = getPetById(petId);
        petList.remove(pet);
    }

    @Override
    public List<PetDto> getAllPets() {
        return petList;
    }
}
