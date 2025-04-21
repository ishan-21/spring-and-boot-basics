package com.ishan.spring_boot_application.repository;

import com.ishan.spring_boot_application.Application;
import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("prod")
@Repository
public class OwnerRepositoryImplForProd implements OwnerRepository{

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OwnerRepositoryImplForProd.class);

    @Value("${owner.found}")
    private String ownerFound;
    @Value("${owner.not.found}")
    private String ownerNotFound;
    public OwnerRepositoryImplForProd() {
        LOGGER.info("OwnerRepositoryImplForProd bean has been created");
    }

    @Override
    public String findOwner(int ownerId) throws OwnerNotFoundException {
        return ownerFound + ownerId;
    }
}
