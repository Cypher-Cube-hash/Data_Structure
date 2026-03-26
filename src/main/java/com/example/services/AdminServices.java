package com.example.services;

import com.example.repositories.interfaces.IAdminRepository;

public class AdminServices {
    IAdminRepository repo;

    public AdminServices(IAdminRepository repo) {
        this.repo = repo;
    }

    
}
