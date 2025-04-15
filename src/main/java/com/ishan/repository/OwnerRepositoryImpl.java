package com.ishan.repository;

import org.springframework.stereotype.Repository;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository{

    public OwnerRepositoryImpl(){
        System.out.println("OwnerRepository bean created");
    }

    @Override
    public String findOwner(int ownerId) {
        return "found owner with ownerId: " + ownerId;
    }

}
