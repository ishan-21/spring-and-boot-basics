package com.ishan.petistaan.repository;

import com.ishan.petistaan.entity.Pet;
import com.ishan.petistaan.exception.PetNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    @Query("SELECT AVG(YEAR(CURRENT_DATE()) - YEAR(p.birthDate)) FROM Pet p")
    Optional<Double> findAverageAgeOfPets();

    @Transactional
    @Modifying
    @Query("UPDATE Pet p SET p.name = :newName WHERE p.id = :petId")
    void updatePetName(@Param("petId") int petId, @Param("newName") String newName);
}
