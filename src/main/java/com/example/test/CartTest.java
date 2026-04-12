package com.example.test;

import com.example.models.CartItem;
import com.example.services.CartService;

public class CartTest {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        CartItem item1 = new CartItem("qhde16xj","1", "Laptop", 1, 999.99);
        CartItem item2 = new CartItem("qhde16xj","2", "Mouse", 2, 19.99);
        CartItem item3 = new CartItem("23fdghjj","3", "Keyboard", 1, 49.99);

        cartService.addItem("qhde16xj",item1);
        cartService.addItem("qhde16xj",item2);
        cartService.addItem("23fdghjj",item3);


        // cartService.getAllCartItems().forEach(System.out::println);

        // cartService.showCart();

        // cartService.undo();
        cartService.getCartItems("qhde16xj").forEach(System.out::println);

        // cartService.showCart();

        // cartService.undo();

        // cartService.showCart();

        // cartService.undo();
        // cartService.redo();
        // cartService.showCart();
    }
}
