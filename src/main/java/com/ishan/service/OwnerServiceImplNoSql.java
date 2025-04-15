package com.ishan.service;

import com.ishan.repository.OwnerRepository;
import com.ishan.repository.OwnerRepositoryImplNoSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("OwnerServiceImplNoSql")
public class OwnerServiceImplNoSql implements OwnerService{

    private final OwnerRepository ownerRepository;

    public OwnerServiceImplNoSql(@Autowired @Qualifier("OwnerRepositoryImplNoSql") OwnerRepository ownerRepository) {
        // the qualifier is used to specify which implementation of the OwnerRepository to use
        System.out.println("OwnerServiceImplNoSql bean created");
        this.ownerRepository = ownerRepository;
    }

    @Override
    public String findOwner(int ownerId) {
        return ownerRepository.findOwner(ownerId);
    }
}
