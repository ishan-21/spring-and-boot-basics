package com.ishan.spring_boot_application.repository;

import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository{

    @Value("${owner.found}")
    private String ownerFound;
    @Value("${owner.not.found}")
    private String ownerNotFound;
    public OwnerRepositoryImpl() {
        System.out.println("OwnerRepositoryImpl bean created");
    }

    @Override
    public String findOwner(int ownerId) throws OwnerNotFoundException {
        if(ownerId%2 == 0){
            return ownerFound + ownerId;
        }
        throw new OwnerNotFoundException( ownerNotFound + ownerId);
    }
}
