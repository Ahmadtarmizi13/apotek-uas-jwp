package com.apk.apotek.rest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.apk.apotek.domain.Medicine;
import com.apk.apotek.service.MedicineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class MedicineController {
    
    private final MedicineService medicineService;

    @GetMapping("/medicines")
    public String getMedicines(Model model) {
        model.addAttribute("medicines", medicineService.getMedicines());
        return "medicines";
    }

    
    @GetMapping("/signup-medicine")
    public String showSignupForm(Medicine medicine) {
        return "add-medicine";
    }

    @PostMapping("/medicines")
    public String addMedicines(@Valid Medicine medicine, BindingResult bindingResult, Model model) {
        String code = medicine.getCode();

        boolean exists = medicineService.findMedicineByCode(code).isPresent();

        if (exists) {
            throw new IllegalArgumentException("medicine with code:" + code + "is already exist");
        }

        medicineService.save(medicine);

        model.addAttribute("medicines", medicineService.getMedicines());
        return "customers";
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    @GetMapping(value = "/medicines/{code}")
    public ResponseEntity<Medicine> findMedicine(@PathVariable("code") String code) {
        Optional<Medicine> medicineOptional = medicineService.findMedicineByCode(code);
        if (medicineOptional.isPresent()) {
            return new ResponseEntity<>(medicineOptional.get(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping(value = "/medicines/{code}")
    public String updateMedicine(@PathVariable("code") String code,
            Medicine medicine,
            BindingResult result, Model model) {

        final Optional<Medicine> optionalMedicine = medicineService.findMedicineByCode(medicine.getCode());
        if (optionalMedicine.isEmpty()) {
            throw new ServiceException("medicine with code:" + code + "is not exists");
        }

        medicineService.update(medicine);

        model.addAttribute("medicines", medicineService.getMedicines());
        return "redirect:/medicines";
    }

    @GetMapping("/edit/{code}")
    public String showUpdateForm(@PathVariable("code") String code, Model model) {
        final Optional<Medicine> optionalMedicine = medicineService.findMedicineByCode(code);
        if (optionalMedicine.isEmpty()) {
            throw new ServiceException("doctor with code:" + code + "is not exists");
        }
        final Medicine medicineToBeUpdated = optionalMedicine.get();

        model.addAttribute("medicine", medicineToBeUpdated);
        return "update-medicine";
    }

    @GetMapping(value = "/medicines/{code}/delete")
    public String deleteMedicine(@PathVariable("code") String code) {
        medicineService.delete(code);
        return "redirect:/doctors";
    }
}
