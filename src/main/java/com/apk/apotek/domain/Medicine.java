package com.apk.apotek.domain;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.experimental.Accessors;

import lombok.Getter;
import lombok.Setter;


@Accessors(chain = true)
@Getter
@Setter

public class Medicine {

    private String code;

    @NotBlank(message = "medicine name is required")
    @Size(min = 3, max = 50)
    private String medicineName;

    @NotBlank(message = "medicine type is required")
    @Size(min = 3, max = 50)
    private String medicineType;

    @NotNull(message = "price is required")
    @Size(min = 3, max = 50)
    private String price;

    

    
    

    public Medicine() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code= code;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }


    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }

    

}