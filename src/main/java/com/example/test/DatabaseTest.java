package com.example.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.example.models.Address;
import com.example.models.User; // replace with any entity
import com.example.repositories.UserRepository;
import com.example.repositories.interfaces.IUserRepository;
import com.example.services.UserServices;

// import java.util.List;

public class DatabaseTest {

    public static void main(String[] args) {

        // 1️⃣ Create EntityManagerFactory with your persistence unit name
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

        // 2️⃣ Create EntityManager
        EntityManager em = emf.createEntityManager();

        IUserRepository userRepository = new UserRepository(em); // Replace with actual implementation
        UserServices userServices = new UserServices(userRepository); // Pass your actual

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
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setEmail("john.doe@example.com");
        newUser.setAddress(new Address("labyrint", "City", "Country", "PostalCode", "Region")); // Set address if needed
        userServices.registerUser("John", "Doe", "john.doe@example.com");

        em.close();
        emf.close();
 
    }
}