package com.example.services;

import com.example.repositories.interfaces.IHistoryRepository;

public class HistoryServices {
    private IHistoryRepository repo;

    public HistoryServices(IHistoryRepository repo) {
        this.repo = repo;
    }

    
    
}
