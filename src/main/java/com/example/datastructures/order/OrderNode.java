package com.example.datastructures.order;

import com.example.datastructures.cart.CartItem;
import com.example.models.Order;

public class OrderNode {
    private Order order;
    private CartItem cartItem;
    private OrderNode next;

    public OrderNode(CartItem cartItem) {
        this.cartItem = cartItem;
        this.next = null;
    }

    public OrderNode(Order order) {
        this.order = order;
        this.next = null;
    }

    //Accessors 
    public CartItem getCartItem(){
        return cartItem;
    }
    public Order getOrder(){
        return order;
    }
    public OrderNode getNext(){
        return next;
    }

    //Mutators
    public void setCartItem(CartItem cartItem){
        this.cartItem = cartItem;
    }
    public void setNext(OrderNode next){
        this.next = next;
    }
}
