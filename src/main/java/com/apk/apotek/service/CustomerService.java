package com.apk.apotek.service;

import java.util.List;
import java.util.Optional;

import com.apk.apotek.domain.Customer;

public interface CustomerService {
    
    List<Customer> getCustomers();

    Optional<Customer> findCustomerById (final String customerId);

    void save(Customer customer);

    void delete(final String customerId);
    
    void update(final Customer customer);
}
