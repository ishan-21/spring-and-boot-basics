package com.ishan.spring_boot_application.service;

import com.ishan.spring_boot_application.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype") // this will create a new instance of the bean every time it is requested
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
