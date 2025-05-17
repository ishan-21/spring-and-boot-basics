package com.ishan.petistaan.service;

import com.ishan.petistaan.dto.OwnerDto;

import java.util.List;

public interface OwnerService {

    void saveOwner(OwnerDto ownerDto);

    void updatePetName(int ownerId, String petName);

    void deleteOwner(int ownerId);

    OwnerDto getOwnerById(int ownerId);

    List<OwnerDto> getAllOwners();
}
