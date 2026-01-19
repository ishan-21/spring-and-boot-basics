package com.ishan.petistaan.service.impl;

import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.exception.PetNotFoundException;
import com.ishan.petistaan.repository.PetRepository;
import com.ishan.petistaan.service.PetService;
import com.ishan.petistaan.util.PetMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PetServiceImpl.class);

    @Override
    public PetDto findPet(int petId) {
        try {
            return petRepository.findById(petId)
                    .map(petMapper::petToPetDto)
                    .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + petId));
        } catch (PetNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePetName(int petId, String newName){
        petRepository.updatePetName(petId, newName);
    }

    @Override
    public Double findAverageAgeOfPets() {
        return petRepository.findAverageAgeOfPets()
                .orElse(0.0);
    }
}
