package com.example.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Stack;

import com.example.enums.TypeProduct;
import com.example.models.Account;
import com.example.models.Address;
import com.example.models.Order;
import com.example.models.User; // replace with any entity
import com.example.repositories.OrderRepository;
import com.example.repositories.UserRepository;
import com.example.repositories.interfaces.IOrderRepository;
import com.example.repositories.interfaces.IUserRepository;
import com.example.services.OrderServices;
import com.example.services.UserServices;
import com.example.utils.IDGenerator;

// import java.util.List;

public class DatabaseTest {

    public static void main(String[] args) {

        // 1️⃣ Create EntityManagerFactory with your persistence unit name
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

        // 2️⃣ Create EntityManager
        EntityManager em = emf.createEntityManager();

        IUserRepository userRepository = new UserRepository(em); // Replace with actual implementation
        UserServices userServices = new UserServices(userRepository); // Pass your actual

        IOrderRepository orderRepository = new OrderRepository(em); // Replace with actual implementation
        OrderServices orderServices = new OrderServices(orderRepository); // Pass your actual implementation

        try {
            // Test database connection
            em.getTransaction().begin();
            System.out.println("Database connection successful!");

            // You can also perform CRUD operations here to test further
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } finally {
            em.getTransaction().commit();
        }

        User newUser = new User();
        newUser.setFirstName("Peter");
        newUser.setLastName("Doe");
        newUser.setEmail("peter.doe@example.com");
        newUser.setAddress(new Address("Gayle", "City", "Country", "PostalCode", "Region")); // Set address if needed
        userServices.registerUser(newUser);

        Order newOrder = new Order();
        newOrder.setProductId("uyr67h32gc");
        newOrder.setAccountId(IDGenerator.idGenerator(10)); // Set account ID if needed
        newOrder.setProductType(TypeProduct.ELECTRONICS); // Set product type if needed
        newOrder.setQuantity(2);
        newOrder.setPrice(19.99f);
        newOrder.setDiscount(5.99f);
        newOrder.setTax(10);
        orderServices.saveOrder(newOrder);

        // userServices.registerUser(
        //     "John", "Doe", "john.doe@example.com", 
        //     new Address("labyrint", "City", "Country", "PostalCode", "Region")
        // );

        em.close();
        emf.close();
 
    }
}