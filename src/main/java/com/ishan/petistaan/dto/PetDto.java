package com.ishan.petistaan.dto;

import com.ishan.petistaan.enums.Gender;
import com.ishan.petistaan.enums.PetType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"ownerDto"}) // excluding this as it was causing circular reference
public class PetDto {
    private int id;
    private String name;
    private Gender gender;
    private PetType petType;
    private OwnerDto ownerDto;
}
