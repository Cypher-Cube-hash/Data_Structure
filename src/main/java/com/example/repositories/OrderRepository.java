package com.example.repositories;

import java.util.List;
import com.example.models.Order;
import com.example.repositories.interfaces.IOrderRepository;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

@Repository
public class OrderRepository implements IOrderRepository {

    public final EntityManager em;

    public OrderRepository(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public List<Order> findAll() {
        // Implementation to retrieve all orders from the database
        return null; // Placeholder return statement
    }

    @Override
    public Order findById(String orderId) {
        em.getTransaction().begin();
        Order order = em.find(Order.class, orderId);
        em.getTransaction().commit();
        return order;
    }

    @Override
    public void save(Order order) {
        // Implementation to save an order to the database
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    @Override
    public void delete(String orderId) {
        // Implementation to delete an order from the database using its ID
    }
}
