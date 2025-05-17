package com.ishan.petistaan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper=true)
public class DomesticPetDto extends PetDto{
    private LocalDate birthDate;
}
