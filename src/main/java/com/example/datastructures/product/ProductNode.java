package com.example.datastructures.product;

import com.example.models.Product;

public class ProductNode {
    private Product product_entity;
    private ProductNode productNext;

    public ProductNode(Product product) {
        this.product_entity = product;
        this.productNext = null;
    }

    //Accessors
    public Product getProduct() { 
        return product_entity; 
    }
    public ProductNode getProductNext() { 
        return productNext; 
    }
    //Mutators
    public void setProduct(Product product) { 
        this.product_entity = product; 
    }
    public void setProductNext(ProductNode next) { 
        this.productNext = next; 
    }

    
}
