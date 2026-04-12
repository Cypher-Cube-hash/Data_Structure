package com.example.models;

public class CartItem {
    private String userId;
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public CartItem() {
        this.userId = "";
        this.productId = "";
        this.productName = "";
        this.quantity = 0;
        this.price = 0.0;
    }

    public CartItem(String userId, String productId, String productName, int quantity, double price) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return productId + " " + productName + " x" + quantity + " ($" + price + ")";
    }

    @Override
    public boolean equals(Object obj) {
        CartItem other = (CartItem) obj;
        return this.getProductId().equals(other.getProductId());
    }

}
