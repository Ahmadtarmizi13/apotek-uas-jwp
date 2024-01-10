package com.apk.apotek.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.apk.apotek.domain.Customer;
import com.apk.apotek.repository.CustomerEntity;
import com.apk.apotek.repository.CustomerEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerEntityRepository customerEntityRepository;

    private Customer map(CustomerEntity entity) {
        final Customer customer = new Customer();
        customer.setCustomerId(entity.getCustomerId());
        customer.setCustomerName(entity.getCustomerName());
        customer.setAddress(entity.getAddress());
        customer.setPhoneNumber(entity.getPhoneNumber());
        customer.setDateofBirth(entity.getDateofBirth());
       
        
        return customer;

    }

    private CustomerEntity map(Customer customer) {
        final CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(customer.getCustomerId());
        entity.setCustomerName(customer.getCustomerName());
        entity.setAddress(customer.getAddress());
        entity.setPhoneNumber(customer.getPhoneNumber());
        entity.setDateofBirth(customer.getDateofBirth());
        
        return entity;

    }

    @Override
    public List<Customer> getCustomers() {
        final List<CustomerEntity> entities = customerEntityRepository.findAll();
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findCustomerById(String customerId) {
        Optional<CustomerEntity> optionalCustomerEntity = customerEntityRepository.findCustomerBycustomerId(customerId);
        if (optionalCustomerEntity.isPresent()) {
            return Optional.of(this.map(optionalCustomerEntity.get()));
        } else {
            return Optional.empty();

        }
    }

    @Override
    public void save(Customer customer) {
        customerEntityRepository.save(this.map(customer));
    }

    @Override
    public void delete(String customerId) {
        Optional<CustomerEntity> optionalEntity = customerEntityRepository.findCustomerBycustomerId(customerId);
        if (optionalEntity.isPresent()) {
            customerEntityRepository.delete(optionalEntity.get());
        } else {
            throw new ServiceException("customer with id {0} not found" + customerId);
        }

    }

    @Override
    public void update(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

   
}



