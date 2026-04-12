package com.example.models;



import com.example.utils.IDGenerator;
import com.example.utils.LinkedList;

public class Cart {
    private String userId;
    private LinkedList<CartItem> items;

    public Cart(String userId) {
        this.userId = userId;
        this.items = new LinkedList<>();
    }

    public String getUserId() {
        return userId;
    }

    public LinkedList<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        // items.add(item);
    }
    
    
}
