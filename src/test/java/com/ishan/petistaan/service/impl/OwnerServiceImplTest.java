package com.ishan.petistaan.service.impl;

import com.ishan.petistaan.dto.OwnerDto;
import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.exception.DuplicateOwnerIdException;
import com.ishan.petistaan.exception.OwnerNotFoundException;
import com.ishan.petistaan.repository.OwnerRepository;
import com.ishan.petistaan.service.PetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = { OwnerServiceImpl.class })
public class OwnerServiceImplTest {

    @Autowired
    private OwnerServiceImpl ownerServiceImpl;

    @MockitoBean
    private OwnerRepository ownerRepository;
    @MockitoBean
    private PetService petService;

    @Test
    void saveOwnerTest() throws DuplicateOwnerIdException {
        OwnerDto ownerDto = new OwnerDto();
        ownerServiceImpl.saveOwner(ownerDto);
        Mockito.verify(ownerRepository, Mockito.times(1)).save(ownerDto);
    }

    @Test
    void updatePetNameTest() throws OwnerNotFoundException {
        // Mock owner data
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(1);
        PetDto petDto = new PetDto();
        petDto.setId(101);
        ownerDto.setPetDto(petDto);

        // Mock repository and service behavior
        Mockito.when(ownerRepository.getOwnerById(1)).thenReturn(ownerDto);

        // Call the method under test
        ownerServiceImpl.updatePetName(1, "Buddy");

        // Verify interactions
        Mockito.verify(petService, Mockito.times(1)).updatePetName(101, "Buddy");
        Mockito.verify(ownerRepository, Mockito.times(1)).getOwnerById(1);
    }

    @Test
    void deleteOwnerTest() throws OwnerNotFoundException {
        ownerServiceImpl.deleteOwner(1);
        Mockito.verify(ownerRepository, Mockito.times(1)).delete(1);
    }

    @Test
    void getOwnerByIdTest() throws OwnerNotFoundException {
        ownerServiceImpl.getOwnerById(1);
        Mockito.verify(ownerRepository, Mockito.times(1)).getOwnerById(1);
    }

    @Test
    void getAllOwnersTest() {
        List<OwnerDto> owners = new ArrayList<>();
        Mockito.when(ownerRepository.getAllOwners()).thenReturn(owners);
        Assertions.assertEquals(owners, ownerServiceImpl.getAllOwners());
        Mockito.verify(ownerRepository, Mockito.times(1)).getAllOwners();
    }
}
