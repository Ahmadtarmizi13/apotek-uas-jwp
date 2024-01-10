package com.apk.apotek.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {
    
    Optional<CustomerEntity> findCustomerBycustomerId(final String customerId);
}
