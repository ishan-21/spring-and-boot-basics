package com.ishan.petistaan.dto;

import com.ishan.petistaan.enums.Gender;
import com.ishan.petistaan.enums.PetType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"ownerDto"}) // excluding this as it was causing circular reference
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class PetDto {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private Gender gender;
    private PetType petType;
    @EqualsAndHashCode.Include
    private OwnerDto ownerDto;
}
