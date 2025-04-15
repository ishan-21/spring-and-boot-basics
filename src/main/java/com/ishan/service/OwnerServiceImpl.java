package com.ishan.service;

import com.ishan.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OwnerServiceImpl")
public class OwnerServiceImpl implements OwnerService{

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(@Autowired OwnerRepository ownerRepository){
        System.out.println("OwnerService bean created");
        this.ownerRepository = ownerRepository;
    }

    @Override
    public String findOwner(int ownerId) {
        return ownerRepository.findOwner(ownerId);
    }
}
