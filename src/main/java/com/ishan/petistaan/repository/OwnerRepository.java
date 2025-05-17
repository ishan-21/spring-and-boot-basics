package com.ishan.petistaan.repository;

import com.ishan.petistaan.dto.OwnerDto;
import com.ishan.petistaan.exception.DuplicateOwnerIdException;
import com.ishan.petistaan.exception.OwnerNotFoundException;

import java.util.List;

public interface OwnerRepository {

    void save(OwnerDto ownerDto) throws DuplicateOwnerIdException;

    void delete(int ownerId) throws OwnerNotFoundException;

    OwnerDto getOwnerById(int ownerId) throws OwnerNotFoundException;

    List<OwnerDto> getAllOwners();
}
