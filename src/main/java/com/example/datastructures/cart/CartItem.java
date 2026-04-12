package com.example.datastructures.cart;

import com.example.models.Product;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(CartItem other) {
        this.product = other.product;
        this.quantity = other.quantity;
    }

    //Accessors
    public Product getProduct(){ 
        return product;
    }
    public int getQuantity(){ 
        return quantity;
    }

    // Modifiers
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return "CartItem[" + product.getProductName() + " x" + quantity + "]";
    }
}