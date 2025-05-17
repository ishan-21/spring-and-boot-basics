package com.ishan.petistaan.repository.impl;

import com.ishan.petistaan.dto.OwnerDto;
import com.ishan.petistaan.exception.DuplicateOwnerIdException;
import com.ishan.petistaan.exception.OwnerNotFoundException;
import com.ishan.petistaan.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository {

    private final List<OwnerDto> ownerList;
    private final String ownerNotFoundMessage;
    private final String duplicateOwnerIdMessage;


    public OwnerRepositoryImpl(@Value("${owner.not.found}")String ownerNotFoundMessage,
                               @Value("${owner.already.exists}")String duplicateOwnerIdMessage) {
        this.ownerNotFoundMessage = ownerNotFoundMessage;
        this.duplicateOwnerIdMessage = duplicateOwnerIdMessage;
        this.ownerList = new ArrayList<>();
    }

    @Override
    public void save(OwnerDto ownerDto) throws DuplicateOwnerIdException {
        if (ownerList.stream().anyMatch(owner -> owner.getId() == ownerDto.getId())) {
            throw new DuplicateOwnerIdException(String.format(duplicateOwnerIdMessage, ownerDto.getId()));
        }
        ownerList.add(ownerDto);
    }

    @Override
    public void delete(int ownerId) throws OwnerNotFoundException {
        OwnerDto owner = getOwnerById(ownerId);
        ownerList.remove(owner);
    }

    @Override
    public OwnerDto getOwnerById(int ownerId) throws OwnerNotFoundException {
        return ownerList.stream()
                .filter(owner -> owner.getId() == ownerId)
                .findFirst()
                .orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFoundMessage, ownerId)));
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return ownerList;
    }
}
