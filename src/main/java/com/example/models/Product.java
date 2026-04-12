package com.example.models;

import jakarta.persistence.*;

import java.util.Objects;

import com.example.enums.TypeProduct;
import com.example.utils.ProductUtils;

import java.time.LocalDate;



/* When learning the documentation of the vaadin 
we learned about jakarta setup. This is the 
first model built to get everything set up
properly */
@Entity
@Table(name = "product")
public class Product {
    
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(unique = true, nullable = false)
    private String skuNumber;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeProduct productType;

    @Column(nullable = false)
    private int productQuantity;

    @Column(length = 25)
    private String productDesc;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    public Product(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }



    public Product(String productName, TypeProduct productType, int productQuantity, String productDesc) {
        this.skuNumber = ProductUtils.skuNumberGenerator(productName);
        this.productName = productName;
        this.productType = productType;
        this.productQuantity = productQuantity;
        this.productDesc = productDesc;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Product(Product product) {
        this.productId = product.productId;
        this.skuNumber = product.skuNumber;
        this.productName = product.productName;
        this.productType = product.productType;
        this.productQuantity = product.productQuantity;
        this.productDesc = product.productDesc;
        this.createdAt = product.createdAt;
        this.updatedAt = product.updatedAt;
    }


    // Accessors
    public int getProductId() { return productId; }
    public String getSkuNumber() { return skuNumber; }
    public String getProductName() { return productName; }
    public TypeProduct getProductType() { return productType; }
    public int getProductQuantity() { return productQuantity; }
    public String getProductDesc() { return productDesc; }
    public LocalDate getCreatedAt() { return createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }

    //Mutators
    public void setProductName(String productName) { this.productName = productName; }
    public void setProductType(TypeProduct productType) { this.productType = productType; }
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }
    public void setProductDesc(String productDesc) { this.productDesc = productDesc; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }



    //Withers
    public Product withProductName(String productName) {
        Product newProduct = new Product(this);
        newProduct.productName = productName;
        return newProduct;
    }

    public Product withProductType(TypeProduct productType) {
        Product newProduct = new Product(this);
        newProduct.productType = productType;
        return newProduct;
    }

    public Product withProductQuantity(int productQuantity) {
        Product newProduct = new Product(this);
        newProduct.productQuantity = productQuantity;
        return newProduct;
    }

    public Product withProductDesc(String productDesc) {
        Product newProduct = new Product(this);
        newProduct.productDesc = productDesc;
        return newProduct;
    }

    public Product withCreatedAt(LocalDate createdAt) {
        Product newProduct = new Product(this);
        newProduct.createdAt = createdAt;
        return newProduct;
    }

    public Product withUpdatedAt(LocalDate updatedAt) {
        Product newProduct = new Product(this);
        newProduct.updatedAt = updatedAt;
        return newProduct;
    }





    @Override
    public String toString() {
        return "Product[productId=" + productId + ", skuNumber=" + skuNumber +
               ", productName=" + productName + ", productType=" + productType + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
               Objects.equals(skuNumber, product.skuNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, skuNumber);
    }

}
