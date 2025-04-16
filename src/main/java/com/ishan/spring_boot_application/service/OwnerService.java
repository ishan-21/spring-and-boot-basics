package com.ishan.spring_boot_application.service;

import com.ishan.spring_boot_application.exception.OwnerNotFoundException;

public interface OwnerService {

    public String findOwner(int ownerId) throws OwnerNotFoundException;
}
