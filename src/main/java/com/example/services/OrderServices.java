package com.example.services;

import java.util.List;
import java.util.Optional;
import com.example.datastructures.cart.CartItem;
import com.example.datastructures.cart.CartLinkedList;
import com.example.datastructures.cart.CartNode;
import com.example.datastructures.cart.CartSession;
import com.example.datastructures.order.OrderLinkedList;
import com.example.datastructures.order.OrderList;
import com.example.datastructures.order.OrderQueue;
import com.example.datastructures.que.Orderque;
import com.example.enums.TypeProduct;
import com.example.models.Order;
import com.example.repositories.OrderRepository;
import com.example.utils.Emailer;

import org.checkerframework.checker.units.qual.t;
// import io.swagger.v3.oas.models.links.Link;
import org.springframework.stereotype.Service;

@Service
public class OrderServices {
    private final OrderRepository repo;
    private OrderLinkedList orderItems;
    private OrderQueue orderQueue;
    private OrderQueue orderProcessedQueue;
    private OrderList orderList;

    public OrderServices(
        OrderRepository repo, 
        OrderQueue orderQueue
    ) {
        this.repo = repo;
        this.orderItems = new OrderLinkedList();
        this.orderQueue = orderQueue;
        this.orderProcessedQueue = new OrderQueue();
        this.orderList = new OrderList();
    }

    public void checkout(CartSession cartSession, double total, String userId, String userEmail) {
        CartLinkedList cart = cartSession.getCartList();

        OrderLinkedList itemsForOrder = new OrderLinkedList();

        // String userId = "jsdduyg932";

        CartNode current = cart.getHead();

        while (current != null) {
            itemsForOrder.add(current.getCartItem());
            current = current.getNext();
        }

        Order order = new Order(userId, userEmail, itemsForOrder, total);

        System.out.println("Order created with ID: " + order.getOrderId() + " and total: $" + String.format("%.2f", total));
        orderQueue.enqueue(order);
        repo.save(order);
    }

    public OrderList getAllOrders() {
        OrderList orders = orderQueue.toList();
        System.out.println("Orders size: " + orders.size());
        return orders;
    }

    public Optional<Order> getOrderById(String id) {
        return repo.findById(id);
    }

    public OrderList getOrdersByUser(String userId) {
        OrderList all = orderQueue.toList();
        OrderList userOrders = new OrderList();

        for (int i = 0; i < all.size(); i++) {
            Order order = all.getIndex(i);

            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }

        return userOrders;
    }

    public OrderList getProcessedOrdersByUser(String userId) {
        OrderList all = orderProcessedQueue.toList();
        OrderList userProcessedOrders = new OrderList();

        for (int i = 0; i < all.size(); i++) {
            Order order = all.getIndex(i);

            if (order.getUserId().equals(userId)) {
                userProcessedOrders.add(order);
            }
        }

        return userProcessedOrders;
    }

    public Order processNextOrder(){
        Order order = orderQueue.dequeue();

        if(order != null){
            orderProcessedQueue.enqueue(order);
            sendProcessedOrderEmail(order);
        }

        return order;
    }

    public void sendProcessedOrderEmail(Order order){
        String orderId = order.getOrderId();
        String userEmail = order.getUserEmail();
        String message = "Your order " + orderId + " has been processed.";
        String subject = "Order Processed";

        Emailer.sendEmail(userEmail, message, subject);
    }

    // public boolean deleteOrder(String id) {
    //     repo.deleteById(id);
    //     bst.delete(id);
    //     return linkedList.remove(id);
    // }

    public Order saveOrder(String productId, String accountId, TypeProduct productType, int quantity, float price, float discount, int tax) {
        // Order order = new Order(productId, accountId, productType, quantity, price, discount, tax);
        // repo.save(order);

        return null;
    }

    public Order saveOrder(Order order) {
        repo.save(order);
        System.out.println("Order saved successfully: ");
        return order;
    }
}

