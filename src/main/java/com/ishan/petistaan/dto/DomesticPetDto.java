package com.ishan.petistaan.dto;

import com.ishan.petistaan.enums.Gender;
import com.ishan.petistaan.enums.PetType;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class DomesticPetDto extends PetDto {
    private LocalDate birthDate;

    @Builder
    public DomesticPetDto(int id, String name, Gender gender, PetType petType, OwnerDto ownerDto, LocalDate birthDate) {
        super(id, name, gender, petType, ownerDto);
        this.birthDate = birthDate;
    }
}
