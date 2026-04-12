package com.example.datastructures.cart;

public class CartNode {

    private CartItem cartItem;
    private CartNode next;

    public CartNode(CartItem cartItem) {
        this.cartItem = cartItem;
        this.next = null;
    }

    //Accessors 
    public CartItem getCartItem(){
        return cartItem;
    }
    public CartNode getNext(){
        return next;
    }

    //Mutators
    public void setCartItem(CartItem cartItem){
        this.cartItem = cartItem;
    }
    public void setNext(CartNode next){
        this.next = next;
    }
}