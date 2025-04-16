package com.ishan.spring_boot_application.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class OwnerRepositoryImpl implements OwnerRepository{

    public OwnerRepositoryImpl() {
        System.out.println("OwnerRepositoryImpl bean created");
    }

    @Override
    public String findOwner(int ownerId) {
        return "found owner with id " + ownerId;
    }
}
