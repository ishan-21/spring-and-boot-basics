package com.ishan.service;

import com.ishan.repository.OwnerRepository;

public class OwnerServiceImpl implements OwnerService{

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository){
        System.out.println("OwnerService bean created");
        this.ownerRepository = ownerRepository;
    }

    @Override
    public String findOwner(int ownerId) {
        return ownerRepository.findOwner(ownerId);
    }
}
