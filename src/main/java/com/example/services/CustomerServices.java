package com.example.services;

import org.springframework.stereotype.Service;
import com.example.models.Customer;
import com.example.models.User;
import com.example.repositories.CustomerRepository;
import com.example.repositories.UserRepository;

@Service
public class CustomerServices {

    private final CustomerRepository customerRepository;

    public CustomerServices(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getById(String customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

}