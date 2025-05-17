package com.ishan.petistaan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
public class WildPetDto extends PetDto{
    private String birthPlace;
}
