package com.example.actions;


import com.example.utils.LinkedList;

import com.example.enums.TypeAction;
import com.example.models.CartItem;

public class CartAction implements Action {
    private CartItem item;
    private TypeAction type;
    private LinkedList<CartItem> cart;

    public CartAction(CartItem item, LinkedList<CartItem> cart, TypeAction type) {
        this.item = item;
        this.cart = cart;
        this.type = type;
    }

    @Override
    public void execute() {
        if(type == TypeAction.ADD) {
            cart.insertAtEnd(item);
        } else if(type == TypeAction.REMOVE) {
            cart.remove(item);
        }
    }

    @Override
    public void undo() {
        if(type == TypeAction.ADD) {
            cart.remove(item);
        } else if(type == TypeAction.REMOVE) {
            cart.insertAtEnd(item);
        }
    };

    @Override
    public void redo() {};

    @Override
    public String toString() {
        return "Action: " + type + " - " + item.toString();
    }
}
