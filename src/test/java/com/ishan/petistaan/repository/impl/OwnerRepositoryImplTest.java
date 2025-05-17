package com.ishan.petistaan.repository.impl;

import com.ishan.petistaan.dto.OwnerDto;
import com.ishan.petistaan.exception.DuplicateOwnerIdException;
import com.ishan.petistaan.exception.OwnerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OwnerRepositoryImplTest {

    @Autowired
    private OwnerRepositoryImpl ownerRepositoryImpl;

    @BeforeEach
    void setUp() {
        ownerRepositoryImpl.getAllOwners().clear(); // Clear the list before each test
    }

    @Test
    void saveTest() throws DuplicateOwnerIdException, OwnerNotFoundException {
        OwnerDto owner = new OwnerDto();
        owner.setId(1);
        owner.setFirstName("John");

        ownerRepositoryImpl.save(owner);
        OwnerDto savedOwner = ownerRepositoryImpl.getOwnerById(1);
        assertNotNull(savedOwner);
        assertEquals(1, savedOwner.getId());
        assertEquals("John", savedOwner.getFirstName());
    }

    @Test
    void saveThrowsExceptionWhenDuplicateId() throws DuplicateOwnerIdException {
        OwnerDto owner = new OwnerDto();
        owner.setId(1);
        owner.setFirstName("John");
        ownerRepositoryImpl.save(owner);

        assertThrows(DuplicateOwnerIdException.class, () -> ownerRepositoryImpl.save(owner));
    }

    @Test
    void deleteTest() throws DuplicateOwnerIdException, OwnerNotFoundException {
        OwnerDto owner = new OwnerDto();
        owner.setId(1);
        owner.setFirstName("John");
        ownerRepositoryImpl.save(owner);

        ownerRepositoryImpl.delete(1);
        assertThrows(OwnerNotFoundException.class, () -> ownerRepositoryImpl.getOwnerById(1));
    }

    @Test
    void deleteThrowsExceptionWhenNotFound() {
        OwnerNotFoundException exception = assertThrows(OwnerNotFoundException.class, () -> ownerRepositoryImpl.delete(1));
    }

    @Test
    void getOwnerByIdTest() throws DuplicateOwnerIdException, OwnerNotFoundException {
        OwnerDto owner = new OwnerDto();
        owner.setId(1);
        owner.setFirstName("John");
        ownerRepositoryImpl.save(owner);

        OwnerDto retrievedOwner = ownerRepositoryImpl.getOwnerById(1);
        assertNotNull(retrievedOwner);
        assertEquals(1, retrievedOwner.getId());
        assertEquals("John", retrievedOwner.getFirstName());
    }

    @Test
    void getOwnerByIdThrowsExceptionWhenNotFound() {
        OwnerNotFoundException exception = assertThrows(OwnerNotFoundException.class, () -> ownerRepositoryImpl.getOwnerById(1));
    }

    @Test
    void getAllOwnersTest() throws DuplicateOwnerIdException {
        OwnerDto owner1 = new OwnerDto();
        owner1.setId(1);
        owner1.setFirstName("John");

        OwnerDto owner2 = new OwnerDto();
        owner2.setId(2);
        owner2.setFirstName("Jane");

        ownerRepositoryImpl.save(owner1);
        ownerRepositoryImpl.save(owner2);

        List<OwnerDto> owners = ownerRepositoryImpl.getAllOwners();
        assertEquals(2, owners.size());
        assertTrue(owners.contains(owner1));
        assertTrue(owners.contains(owner2));
    }
}