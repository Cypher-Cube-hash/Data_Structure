package com.example.repositories;

import java.util.List;

import com.example.models.Product;
import com.example.repositories.interfaces.IProductRepository;

public class ProductRepository implements IProductRepository {
    
    @Override
    public List<Product> findAll() {
        // Implementation to retrieve all products from the database
        return null; // Placeholder return statement
    }   

    @Override
    public Product findById(Integer id) {
        // Implementation to retrieve a specific product by its ID from the database
        return null; // Placeholder return statement
    }

    @Override
    public void save(Product product) { 
        // Implementation to create a new product in the database
    }

    @Override
    public void delete(Integer id) {    
        // Implementation to delete a product from the database using its ID
    }
}
