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

import com.apk.apotek.domain.Customer;
import com.apk.apotek.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    
    @GetMapping("/customers")
    public String getCustomers(Model model) {
        model.addAttribute("customers", customerService.getCustomers());
        return "customers";
    }
    
    @GetMapping("/signup-customer")
    public String showSignupForm(Customer customer) {
        return "add-customer";
    }
    

    @PostMapping("/customers")
    public String addCustomer(@Valid Customer customer, BindingResult bindingResult, Model model) {
        String customerId = customer.getCustomerId();

        boolean exists = customerService.findCustomerById(customerId).isPresent();

        if (exists) {
            throw new IllegalArgumentException("customer with customerId:" + customerId + "is already exist");
        }

        customerService.save(customer);

        model.addAttribute("customers", customerService.getCustomers());
        return "customers";
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    @GetMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> findCustomer(@PathVariable("customerId") String customerId) {
        Optional<Customer> customerOptional = customerService.findCustomerById(customerId);
        if (customerOptional.isPresent()) {
            return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping(value = "/customers/{customerId}")
    public String updateCustomer(@PathVariable("customerId") String customerId,
            Customer customer,
            BindingResult result, Model model) {

        final Optional<Customer> optionalCustomer = customerService.findCustomerById(customer.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            throw new ServiceException("customer with customerId:" + customerId + "is not exists");
        }

        customerService.update(customer);

        model.addAttribute("customers", customerService.getCustomers());
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{customerId}")
    public String showUpdateForm(@PathVariable("customerId") String customerId, Model model) {
        final Optional<Customer> optionalCustomer = customerService.findCustomerById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new ServiceException("customer with customerId:" + customerId + "is not exists");
        }
        final Customer customerToBeUpdated = optionalCustomer.get();

        model.addAttribute("customer", customerToBeUpdated);
        return "update-customer";
    }

    @GetMapping(value = "/customers/{customerId}/delete")
    public String deleteCustomer(@PathVariable("customerId") String customerId) {
        customerService.delete(customerId);
        return "redirect:/customers";
    }
}
