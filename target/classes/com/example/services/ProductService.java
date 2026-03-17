package com.example.services;

import com.example.models.Product;
import com.example.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService{
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public void save(Product product){
        repository.save(product);
    }

    public void delete(Product product){
        repository.delete(product);
    }


}