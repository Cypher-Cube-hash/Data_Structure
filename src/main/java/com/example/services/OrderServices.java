package com.example.services;

import org.springframework.stereotype.Service;
import com.example.models.Order;
import com.example.repositories.interfaces.IOrderRepository;

@Service
public class OrderServices {
    private IOrderRepository repo;

    public OrderServices(IOrderRepository repo) {
        this.repo = repo;
    }

    public void createOrder() {
        // Implementation to create an order
    }

    public Order getOrderById(String id) {
        return repo.findById(id);
    }
}
