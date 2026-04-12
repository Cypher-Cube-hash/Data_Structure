package com.example.test;

import com.example.enums.TypeProduct;
import com.example.models.Order;
import com.example.repositories.OrderRepository;
import com.example.repositories.interfaces.IOrderRepository;
import com.example.services.OrderServices;
import com.example.utils.IDGenerator;
import com.example.utils.Stack;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class OrderTest {
    public static void main(String[] args) {
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        // EntityManager em = emf.createEntityManager();

        // IOrderRepository orderRepo = new OrderRepository(em);
        // OrderServices orderServices = new OrderServices(orderRepo);

        Order newOrder = new Order();
        newOrder.setProductId("uyr67h32pi");
        newOrder.setAccountId(IDGenerator.idGenerator(10)); // Set account ID if needed
        newOrder.setProductType(TypeProduct.ELECTRONICS); // Set product type if needed
        newOrder.setQuantity(2);
        newOrder.setPrice(19.99f);
        newOrder.setDiscount(5.99f);
        newOrder.setTax(10);
        // orderServices.saveOrder(newOrder);

        Order newOrder2 = new Order();
        newOrder2.setProductId("udbuw077pi");
        newOrder2.setAccountId(IDGenerator.idGenerator(10)); // Set account ID if needed
        newOrder2.setProductType(TypeProduct.ELECTRONICS); // Set product type if needed
        newOrder2.setQuantity(6);
        newOrder2.setPrice(30.99f);
        newOrder2.setDiscount(7.99f);
        newOrder2.setTax(12);

        Stack<Order> orderStack = new Stack<>();
        orderStack.push(newOrder);
        orderStack.push(newOrder2);
        // orderStack.toString();
        orderStack.stackDisplay();

        // String orderId = "hhqfrirg3se";

        // Order order = orderServices.getOrderById(orderId);

        // System.out.println("Order ID: " + order.getOrderId());
        // System.out.println("Product ID: " + order.getProductId());
        // System.out.println("Account ID: " + order.getAccountId());
        // System.out.println("Product Type: " + order.getProductType());  
        // System.out.println("Quantity: " + order.getQuantity());
        // System.out.println("Price: " + order.getPrice());
        // System.out.println("Discount: " + order.getDiscount());
        // System.out.println("Tax: " + order.getTax());

        // em.close();
        // emf.close();
    }
}
