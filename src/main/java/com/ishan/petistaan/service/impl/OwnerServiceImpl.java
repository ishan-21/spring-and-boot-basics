package com.ishan.petistaan.service.impl;

import com.ishan.petistaan.dto.OwnerDto;
import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.exception.OwnerNotFoundException;
import com.ishan.petistaan.repository.OwnerRepository;
import com.ishan.petistaan.service.OwnerService;
import com.ishan.petistaan.service.PetService;
import com.ishan.petistaan.util.OwnerMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetService petService;
    private final OwnerMapper ownerMapper;

    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OwnerServiceImpl.class);

    @Override
    public void saveOwner(OwnerDto ownerDto) {
        ownerRepository.save(ownerMapper.ownerDTOToOwner(ownerDto));
    }

    @Override
    public void updatePetDetails(int ownerId, String petName){
        OwnerDto ownerDto = findOwner(ownerId);
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
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public OwnerDto findOwner(int ownerId) {
        try {
            return ownerRepository.findById(ownerId)
                    .map(ownerMapper::ownerToOwnerDto)
                    .orElseThrow(() -> new OwnerNotFoundException("Pet not found with id: " + ownerId));
        } catch (OwnerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OwnerDto> findAllOwners() {
        return ownerRepository.findAll().stream().map(ownerMapper::ownerToOwnerDto).toList();
    }

    @Override
    public List<Object[]> findIdAndFirstNameAndLastNameAndPetNameOfPaginatedOwners(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ownerRepository.findIdAndFirstNameAndLastNameAndPetNameOfPaginatedOwners(pageable);
    }
}
