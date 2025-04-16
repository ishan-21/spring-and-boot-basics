package com.ishan.spring_boot_application.repository;

import com.ishan.spring_boot_application.exception.OwnerNotFoundException;

public interface OwnerRepository{

    public String findOwner(int ownerId) throws OwnerNotFoundException;
}
