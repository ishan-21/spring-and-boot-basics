package com.ishan.spring_boot_application.service;

import com.ishan.spring_boot_application.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(@Autowired OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
        System.out.println("OwnerServiceImpl bean created");
    }

    @Override
    public String findOwner(int ownerId) {
        return ownerRepository.findOwner(ownerId);
    }
}
