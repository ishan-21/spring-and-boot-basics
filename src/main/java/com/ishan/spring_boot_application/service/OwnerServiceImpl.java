package com.ishan.spring_boot_application.service;

import com.ishan.spring_boot_application.Application;
import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import com.ishan.spring_boot_application.repository.OwnerRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OwnerServiceImpl.class);
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(@Autowired OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
        LOGGER.info("OwnerServiceImpl bean created");
    }

    @Override
    public String findOwner(int ownerId) throws OwnerNotFoundException {
        return ownerRepository.findOwner(ownerId);
    }
}
