package com.ishan.spring_boot_application.repository;

import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OwnerRepositoryImplForProd.class) // so that only this class is run here
@TestPropertySource("classpath:custom-properties/messages.properties") // to load the test properties file
@ActiveProfiles("prod") // so that bean of OwnerRepositoryImplForProd is created sucessfully
class OwnerRepositoryImplForProdTest {

    @Autowired // autowired can be used only for the object whose class is being tested
    private OwnerRepositoryImplForProd ownerRepositoryImplForProd;

    @Test
    void testFindOwnerForAnyOwnerId() throws OwnerNotFoundException {
        int ownerId = 2;
        String actualResult = ownerRepositoryImplForProd.findOwner(ownerId);
        String expectedResult = "found owner with id " + ownerId;
        assertEquals(expectedResult, actualResult);
    }

}