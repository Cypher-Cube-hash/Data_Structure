package com.example.models;

import java.time.LocalDate;
import com.example.enums.TypeProduct;
import com.example.utils.IDGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    private String orderId;
    private String productId;
    private String accountId;

    @Enumerated(EnumType.STRING)
    private TypeProduct productType;

    private int quantity;
    private float price;
    private float discount;
    private int tax;
    private LocalDate orderDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;    

    public Order() {
        this.orderId = IDGenerator.idGenerator(10);
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.orderDate = LocalDate.now();
    }

    public Order(String productId, String accountId, TypeProduct productType, int quantity, float price, float discount, int tax) {
        this();
        this.productId = productId;
        this.accountId = accountId;
        this.productType = productType;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.tax = tax;
    }

    public Order(Order order) {
        this();
        this.productId = order.productId;
        this.accountId = order.accountId;
        this.productType = order.productType;
        this.quantity = order.quantity;
        this.price = order.price;
        this.discount = order.discount;
        this.tax = order.tax;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }  

    public String getProductId() {
        return productId;
    }

    public String getAccountId() {
        return accountId;
    }

    public TypeProduct getProductType() {
        return productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public int getTax() {
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

    // Setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setProductType(TypeProduct productType) {
        this.productType = productType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Order withOrderId(String orderId) {
        Order newOrder = new Order(this);
        newOrder.orderId = orderId;
        return newOrder;
    }

    public Order withProductId(String productId) {
        Order newOrder = new Order(this);
        newOrder.productId = productId;
        return newOrder;
    }

    public Order withAccountId(String accountId) {
        Order newOrder = new Order(this);
        newOrder.accountId = accountId;
        return newOrder;
    }

    public Order withProductType(TypeProduct productType) {
        Order newOrder = new Order(this);
        newOrder.productType = productType;
        return newOrder;
    }   

    public Order withQuantity(int quantity) {
        Order newOrder = new Order(this);
        newOrder.quantity = quantity;
        return newOrder;
    }

    public Order withPrice(float price) {
        Order newOrder = new Order(this);
        newOrder.price = price;
        return newOrder;
    }

    public Order withDiscount(byte discount) {
        Order newOrder = new Order(this);
        newOrder.discount = discount;
        return newOrder;
    }

    public Order withTax(int tax) {
        Order newOrder = new Order(this);
        newOrder.tax = tax;
        return newOrder;
    }

    public Order withOrderDate(LocalDate orderDate) {
        Order newOrder = new Order(this);
        newOrder.orderDate = orderDate;
        return newOrder;
    }

    public Order withCreatedAt(LocalDate createdAt) {
        Order newOrder = new Order(this);
        newOrder.createdAt = createdAt;
        return newOrder;
    }

    public Order withUpdatedAt(LocalDate updatedAt) {
        Order newOrder = new Order(this);
        newOrder.updatedAt = updatedAt;
        return newOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
               "orderId='" + orderId + '\'' +
               ", productId='" + productId + '\'' +
               ", accountId='" + accountId + '\'' +
               ", productType=" + productType +
               ", quantity=" + quantity +
               ", price=" + price +
               ", discount=" + discount +
               ", tax=" + tax +
               ", orderDate=" + orderDate +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }




}
