package com.example.services;

import com.example.repositories.interfaces.IProductRepository;

public class ProductServices {
    IProductRepository repo;

    public ProductServices(IProductRepository repo) {
        this.repo = repo;
    }

    
}
