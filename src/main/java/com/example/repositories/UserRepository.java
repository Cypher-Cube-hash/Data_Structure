package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.models.User;
import com.example.repositories.interfaces.IUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Repository
public class UserRepository implements IUserRepository {

    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public List<User> findAll() {
        // Implementation to retrieve all users from the database
        return null; // Placeholder return statement
    }

    @Override
    public User findById(String id) {
        // Implementation to find a user by their ID
        return null; // Placeholder return statement
    }

    @Override
    public void save(User entity) {
        // Implementation to save a user to the database
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        // Implementation to delete a user by their ID
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // Implementation to find a user by their email
        return null; // Placeholder return statement
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        // Implementation to find users by their first name
        return null; // Placeholder return statement
    }

    @Override
    public List<User> findByLastName(String lastName) {
        // Implementation to find users by their last name
        return null; // Placeholder return statement
    }

    @Override
    public boolean existsByEmail(String email) {
        // Implementation to check if a user exists by their email
        return false; // Placeholder return statement
    }
}
