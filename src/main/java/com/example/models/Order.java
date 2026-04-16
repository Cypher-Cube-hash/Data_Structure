package com.example.models;

import java.time.LocalDate;

import com.example.datastructures.order.OrderLinkedList;
import com.example.utils.IDGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order {    
    @Id
    private String orderId;
    private String userId;
    @Transient
    private OrderLinkedList items;
    private double total;
    private double discount;
    private double tax;
    private LocalDate orderDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;   

    public Order() {
        this.orderId = IDGenerator.idGenerator(10);
        this.tax = 0;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.orderDate = LocalDate.now();
    }
    

    public Order(String userId, OrderLinkedList items, double total) {
        this.orderId = IDGenerator.idGenerator(10);
        this.userId = userId;
        this.items = items;
        this.total = total;
        this.tax = 0;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.orderDate = LocalDate.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId(){
        return this.userId;
    }

    public OrderLinkedList getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTax() {
        return tax;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
}
