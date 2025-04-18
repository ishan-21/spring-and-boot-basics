package com.ishan.spring_boot_application.repository;

import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("prod")
@Repository
public class OwnerRepositoryImplForProd implements OwnerRepository{

    @Value("${owner.found}")
    private String ownerFound;
    @Value("${owner.not.found}")
    private String ownerNotFound;
    public OwnerRepositoryImplForProd() {
        System.out.println("OwnerRepositoryImplForProd bean has been created");
    }

    @Override
    public String findOwner(int ownerId) throws OwnerNotFoundException {
        return ownerFound + ownerId;
    }
}
