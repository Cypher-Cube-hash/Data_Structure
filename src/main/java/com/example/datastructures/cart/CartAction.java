package com.example.datastructures.cart;

public class CartAction {

    public enum Type { ADD, REMOVE, QUANTITY }

    private final Type type;
    private final CartItem item;       
    private final int oldQuantity;

    public CartAction(Type type, CartItem item) {
        this.type = type;
        this.item = new CartItem(item);
        this.oldQuantity = item.getQuantity();
    }

    public CartAction(Type type, CartItem item, int oldQuantity) {
        this.type = type;
        this.item = new CartItem(item);
        this.oldQuantity = oldQuantity;
    }

    //Accessors 
    public Type getType(){ 
        return type; 
    }
    public CartItem getItem(){ 
        return item;
    }
    public int getOldQuantity(){ 
        return oldQuantity;
    }

    @Override
    public String toString() {
        return "CartAction[" + type + " " + item + "]";
    }
}