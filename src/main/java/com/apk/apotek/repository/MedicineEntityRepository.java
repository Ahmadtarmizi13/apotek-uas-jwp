package com.apk.apotek.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineEntityRepository extends JpaRepository<MedicineEntity, Long> {

    Optional<MedicineEntity> findByCode(final String code);
    
}