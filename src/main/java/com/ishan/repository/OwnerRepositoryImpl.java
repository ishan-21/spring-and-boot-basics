package com.ishan.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("OwnerRepositoryImpl")
@Primary // this denotes that this will the default bean to be injected for any OwnerRepository type
public class OwnerRepositoryImpl implements OwnerRepository{

    public OwnerRepositoryImpl(){
        System.out.println("OwnerRepository bean created");
    }

    @Override
    public String findOwner(int ownerId) {
        return "found owner with ownerId: " + ownerId;
    }

}
