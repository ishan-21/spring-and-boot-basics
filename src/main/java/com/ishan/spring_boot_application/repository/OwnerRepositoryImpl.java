package com.ishan.spring_boot_application.repository;

import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository{

    public OwnerRepositoryImpl() {
        System.out.println("OwnerRepositoryImpl bean created");
    }

    @Override
    public String findOwner(int ownerId) throws OwnerNotFoundException {
        if(ownerId%2 == 0){
            return "found owner with id " + ownerId;
        }
        throw new OwnerNotFoundException("Owner not found with id " + ownerId);
    }
}
