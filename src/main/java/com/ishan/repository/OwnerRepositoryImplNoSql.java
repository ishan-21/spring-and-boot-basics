package com.ishan.repository;

import org.springframework.stereotype.Repository;

@Repository("OwnerRepositoryImplNoSql")
// This is a NoSQL implementation of the OwnerRepository interface
public class OwnerRepositoryImplNoSql implements OwnerRepository{

    public OwnerRepositoryImplNoSql() {
        System.out.println("OwnerRepositoryImplNoSql bean created");
    }

    @Override
    public String findOwner(int ownerId) {
        return "Found owner with ownerId: " + ownerId + " from NoSQL database";
    }
}
