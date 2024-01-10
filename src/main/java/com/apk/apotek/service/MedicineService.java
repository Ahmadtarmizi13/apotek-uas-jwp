package com.apk.apotek.service;


import java.util.List;
import java.util.Optional;
import com.apk.apotek.domain.Medicine;

public interface MedicineService {
    
    
    List<Medicine> getMedicines();

    Optional<Medicine> findMedicineByCode(final String code);

    void save(Medicine medicine);

    void delete(final String code);

    void update(final Medicine medicine);

}