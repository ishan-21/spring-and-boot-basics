package com.ishan.petistaan.service;

import com.ishan.petistaan.dto.OwnerDto;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    void saveOwner(OwnerDto ownerDto);

    void updatePetDetails(int ownerId, String petName);

    void deleteOwner(int ownerId);

    OwnerDto findOwner(int ownerId);

    List<OwnerDto> findAllOwners();

    List<Object[]> findIdAndFirstNameAndLastNameAndPetNameOfPaginatedOwners(int page, int size);
}
