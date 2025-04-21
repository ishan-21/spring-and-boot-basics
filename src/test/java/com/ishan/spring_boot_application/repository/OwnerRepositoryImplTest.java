package com.ishan.spring_boot_application.repository;

import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OwnerRepositoryImpl.class) // so that only this class is run here
@TestPropertySource("classpath:custom-properties/messages.properties") // to load the test properties file
class OwnerRepositoryImplTest {

    @Autowired // autowired can be used only for the object whose class is being tested
    private OwnerRepositoryImpl ownerRepositoryImpl;

    @Test
    void testFindOwnerForEvenOwnerId() throws OwnerNotFoundException {
        int ownerId = 2;
        String actualResult = ownerRepositoryImpl.findOwner(ownerId);
        String expectedResult = "found owner with id " + ownerId;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testFindOwnerForOddOwnerId() {
        int ownerId = 3;
        OwnerNotFoundException ownerNotFoundException = assertThrows(OwnerNotFoundException.class, () -> {
            ownerRepositoryImpl.findOwner(ownerId);
        });
        String actualResult = ownerNotFoundException.getMessage();
        String expectedResult = "Owner not found with id " + ownerId;
        assertEquals(expectedResult, actualResult);
    }
}