package com.apk.apotek.repository;





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;




@Entity
@Table(name = "medicine")
@Getter
@Setter

public class MedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


     @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "medicine_name", nullable = false)
    private String medicineName;

    @Column(name = "medicine_type")
    private String medicineType;

    @Column(name = "price")
    private String price;


    

}

