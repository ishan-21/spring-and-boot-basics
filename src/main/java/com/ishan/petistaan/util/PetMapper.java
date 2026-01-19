package com.ishan.petistaan.util;

import com.ishan.petistaan.dto.DomesticPetDto;
import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.dto.WildPetDto;
import com.ishan.petistaan.entity.DomesticPet;
import com.ishan.petistaan.entity.Pet;
import com.ishan.petistaan.entity.WildPet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {

    String UNSUPPORTED_PET_INSTANCE = "Unsupported pet instance: %s";

    default PetDto petToPetDto(Pet pet) {
        return switch (pet) {
            case DomesticPet domesticPet -> domesticPetToDomesticPetDto(domesticPet);
            case WildPet wildPet -> wildPetToWildPetDto(wildPet);
            default -> throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE, pet.getClass()));
        };
    }

    @Mapping(target = "ownerDto.petDto", ignore = true)
    @Mapping(source = "owner", target = "ownerDto")
    DomesticPetDto domesticPetToDomesticPetDto(DomesticPet domesticPet);

    @Mapping(target = "ownerDto.petDto", ignore = true)
    @Mapping(source = "owner", target = "ownerDto")
    WildPetDto wildPetToWildPetDto(WildPet wildPet);

}
