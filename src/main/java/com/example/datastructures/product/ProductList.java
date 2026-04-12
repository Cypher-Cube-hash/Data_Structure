package com.example.datastructures.product;

import com.example.models.Product;

public class ProductList {
    private Product[] product_values;
    private int size;

    public ProductList() {
        product_values = new Product[10];
        size = 0;
    }

    public void add(Product product) {
        if (size == product_values.length) {
            resize();
        }
        product_values[size++] = product;
    }

    private void resize() {
        Product[] newData = new Product[product_values.length * 2];
        for (int i = 0; i < product_values.length; i++) {
            newData[i] = product_values[i];
        }
        product_values = newData;
    }

    public Product getIndex(int index) {
        return product_values[index];
    }

    public int size() {
        return size;
    }
}
