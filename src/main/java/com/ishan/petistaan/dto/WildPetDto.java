package com.ishan.petistaan.dto;

import com.ishan.petistaan.enums.Gender;
import com.ishan.petistaan.enums.PetType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class WildPetDto extends PetDto {
    private String birthPlace;

    @Builder
    public WildPetDto(int id, String name, Gender gender, PetType petType, OwnerDto ownerDto, String birthPlace) {
        super(id, name, gender, petType, ownerDto);
        this.birthPlace = birthPlace;
    }
}
