package com.ishan.spring_boot_application.service;

import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import com.ishan.spring_boot_application.repository.OwnerRepository;
import com.ishan.spring_boot_application.repository.OwnerRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = OwnerServiceImpl.class) // so that only this class is run here
@PropertySource("classpath:custom-properties/messages.properties") // to load the test properties file
class OwnerServiceImplTest {

    @Autowired // autowired can be used only for the object whose class is being tested
    private OwnerServiceImpl ownerServiceImpl;
    @MockitoBean // this is used to create a mock object of OwnerRepositoryImpl which WILL BE AUTOWIRED in OwnerServiceImpl
    private OwnerRepository ownerRepository;

    @Test
    void testFindOwnerForEvenOwnerId() throws Exception {
        int ownerId = 2;
        when(ownerRepository.findOwner(ownerId)).thenReturn("found owner with id " + ownerId);
        String actualResult = ownerServiceImpl.findOwner(ownerId);
        String expectedResult = "found owner with id " + ownerId;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testFindOwnerForOddOwnerId() throws Exception {
        int ownerId = 3;
        when(ownerRepository.findOwner(ownerId)).thenThrow(new OwnerNotFoundException("Owner not found with id " + ownerId));
        OwnerNotFoundException ownerNotFoundException = assertThrows(OwnerNotFoundException.class, () -> {
            ownerServiceImpl.findOwner(ownerId);
        });
        String expectedMessage = "Owner not found with id " + ownerId;
        assertEquals(expectedMessage, ownerNotFoundException.getMessage());
    }


}