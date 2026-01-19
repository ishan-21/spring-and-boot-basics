package com.ishan.petistaan.repository;

import com.ishan.petistaan.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    @Query("SELECT o.id, o.firstName, o.lastName, o.pet.name FROM Owner o JOIN o.pet")
    List<Object[]> findIdAndFirstNameAndLastNameAndPetNameOfPaginatedOwners(Pageable pageable);
}
