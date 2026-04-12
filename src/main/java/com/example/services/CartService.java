package com.example.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.actions.CartAction;
import com.example.enums.TypeAction;
import com.example.models.CartItem;
import com.example.utils.LinkedList;
import com.example.utils.Stack;

public class CartService {
    private Map<String, LinkedList<CartItem>> cart = new HashMap<>();
    private Stack<CartAction> undoStack = new Stack<>();
    private Stack<CartAction> redoStack = new Stack<>();

    public CartService() {
      
    }

    public void addItem(String userId, CartItem item) {
        cart.putIfAbsent(userId, new LinkedList<>());
        LinkedList<CartItem> userCart = cart.get(userId);
        CartAction action = new CartAction(item, userCart, TypeAction.ADD);
        action.execute();
        undoStack.push(action);
        redoStack.clear();
    }

    public void removeItem(String userId, CartItem item) {
        LinkedList<CartItem> userCart = cart.get(userId);
        CartAction action = new CartAction(item, userCart, TypeAction.REMOVE);
        action.execute();
        undoStack.push(action);
        redoStack.clear();
    }

    public void undo() {
        if(!undoStack.isEmpty()) {
            CartAction action = undoStack.pop();
            action.undo();
            redoStack.push(action);
        } else {
            System.out.println("Nothing to undo!");
        }
    }

    public void redo() {
        if(!redoStack.isEmpty()) {
            CartAction action = redoStack.pop();
            action.execute();
            undoStack.push(action);
        } else {
            System.out.println("Nothing to redo!");
        }
    }

    public List<CartItem> getCartItems(String userId) {
        LinkedList<CartItem> userCart = cart.get(userId);
        return userCart.toList();
    }

    // public void getAllCartItems() {
    //     cart.toList();
    // }

    // public void showCart() {
    //     cart.showList();
        
    //     undoStack.stackDisplay();
    // }

}
