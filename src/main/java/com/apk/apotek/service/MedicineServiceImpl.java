package com.apk.apotek.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;


import com.apk.apotek.domain.Medicine;

import com.apk.apotek.repository.MedicineEntity;
import com.apk.apotek.repository.MedicineEntityRepository;
import lombok.RequiredArgsConstructor;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class MedicineServiceImpl implements MedicineService {
    private final MedicineEntityRepository MedicineEntityRepository;

    private Medicine map(MedicineEntity entity) {
        final Medicine medicine = new Medicine();
        medicine.setCode(entity.getCode());
        medicine.setMedicineName(entity.getMedicineName());
        medicine.setMedicineType(entity.getMedicineType());
        medicine.setPrice(entity.getPrice());
        

        return medicine;

    }

    private MedicineEntity map(Medicine medicine) {
        final MedicineEntity entity = new MedicineEntity();
        entity.setCode(medicine.getCode());
        entity.setMedicineName(medicine.getMedicineName());
        entity.setMedicineType(medicine.getMedicineType());
        entity.setPrice(medicine.getPrice());
        
        return entity;

    }

    @Override
    public List<Medicine> getMedicines() {
        final List<MedicineEntity> entities = MedicineEntityRepository.findAll();
        return entities.stream()
                .map(this::map)
                .collect(toList());
    }

    @Override
    public Optional<Medicine> findMedicineByCode(String code) {
        Optional<MedicineEntity> optionalMedicineEntity = MedicineEntityRepository.findByCode(code);
        if (optionalMedicineEntity.isPresent()) {
            return Optional.of(this.map(optionalMedicineEntity.get()));
        } else {
            return Optional.empty();

        }
    }

    @Override
    public void save(Medicine medicine) {
        MedicineEntityRepository.save(this.map(medicine));
    }

    @Override
    public void delete(String code) {
        Optional<MedicineEntity> optionalEntity = MedicineEntityRepository.findByCode(code);
        if (optionalEntity.isPresent()) {
            MedicineEntityRepository.delete(optionalEntity.get());
        } else {
            throw new ServiceException("medicine with code {0} not found" + code);
        }

    }

    @Override
    public void update(Medicine medicine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

   
}

