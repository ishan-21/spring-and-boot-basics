package com.ishan.spring_boot_application.repository;

import com.ishan.spring_boot_application.Application;
import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


@Profile("local")
@Repository
public class OwnerRepositoryImpl implements OwnerRepository{

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OwnerRepositoryImpl.class);
    @Value("${owner.found}")
    private String ownerFound;
    @Value("${owner.not.found}")
    private String ownerNotFound;
    public OwnerRepositoryImpl() {
        LOGGER.info("OwnerRepositoryImpl bean created");
    }

    @Override
    public String findOwner(int ownerId) throws OwnerNotFoundException {
        if(ownerId%2 == 0){
            return ownerFound + ownerId;
        }
        throw new OwnerNotFoundException( ownerNotFound + ownerId);
    }
}
