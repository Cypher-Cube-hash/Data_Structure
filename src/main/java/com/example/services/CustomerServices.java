package com.example.services;

import com.example.repositories.interfaces.ICustomerRepository;

public class CustomerServices {
    ICustomerRepository repo;

    public CustomerServices(ICustomerRepository repo) {
        this.repo = repo;
    }

    
}
