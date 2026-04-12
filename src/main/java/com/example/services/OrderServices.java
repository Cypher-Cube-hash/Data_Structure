package com.example.services;

// import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.enums.TypeProduct;
// import com.example.models.Account;
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

    public Order saveOrder(String productId, String accountId, TypeProduct productType, int quantity, float price, float discount, int tax) {
        Order order = new Order(productId, accountId, productType, quantity, price, discount, tax);
        repo.save(order);

        return order;
    }

    public Order saveOrder(Order order) {
        repo.save(order);
        System.out.println("Order saved successfully: " + order.getOrderId());
        return order;
    }
}
