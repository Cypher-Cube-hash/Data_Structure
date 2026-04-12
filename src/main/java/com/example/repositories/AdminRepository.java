package com.example.repositories;

import java.util.List;
import com.example.models.Admin;
import com.example.repositories.interfaces.IAdminRepository;

public class AdminRepository implements IAdminRepository {

    @Override
    public List<Admin> findAll() {
        // Implementation for retrieving all Admin entities from the database
        return null; // Placeholder return statement
    }

    @Override
    public Admin findById(String id) {
        // Implementation for retrieving a specific Admin entity by its ID from the database
        return null; // Placeholder return statement
    }   

    @Override
    public void delete(String id) {
        // Implementation for deleting an Admin entity from the database using its ID
    }
    
    @Override
    public void save(Admin entity) {
        // Implementation for saving an Admin entity to the database
    }
    
}
