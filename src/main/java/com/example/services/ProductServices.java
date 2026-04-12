package com.example.services;

import org.springframework.stereotype.Service;

import com.example.datastructures.product.ProductBst;
import com.example.datastructures.product.ProductLinkedList;
import com.example.datastructures.product.ProductList;
import com.example.models.Product;
import com.example.repositories.ProductRepository;

@Service
public class ProductServices {

    private final ProductRepository productRepository;
    private final ProductLinkedList linkedList;
    private final ProductBst bst;

    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.linkedList = new ProductLinkedList();
        this.bst = new ProductBst();
        loadDataFromDB();
    }

    private void loadDataFromDB() {
        Iterable<Product> products = productRepository.findAll();
        for (Product p : products) {
            linkedList.addToBack(p);
            bst.insert(p);
        }
    }
    public ProductList getAllProducts() {
        return linkedList.toList();
    }

    public Product getById(int id) {
        Product found = bst.searchById(id);
        return found != null ? found : linkedList.findById(id);
    }

    public ProductList searchByName(String name) {
        return bst.searchByName(name);
    }

    public Product addProduct(Product product) {
        Product saved = productRepository.save(product);
        linkedList.addToBack(saved);
        bst.insert(saved);
        return saved;
    }

    public boolean deleteProduct(int id) {
        productRepository.deleteById(id);
        bst.delete(id);
        return linkedList.remove(id);
    }

    public int count() {
        return linkedList.getSize();
    }
}