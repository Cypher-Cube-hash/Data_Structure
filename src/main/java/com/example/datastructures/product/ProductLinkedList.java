package com.example.datastructures.product;

import java.util.ArrayList;

import com.example.models.Product;
public class ProductLinkedList {
    private ProductNode head;
    private int size;

    public ProductLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(Product product) {
        ProductNode newNode = new ProductNode(product);
        newNode.setProductNext(head);
        head = newNode;
        size++;
    }

    public void addToBack(Product product) {
        if (isEmpty()) { add(product); return; }
        ProductNode newNode = new ProductNode(product);
        ProductNode current = head;
        while (current.getProductNext() != null) {
            current = current.getProductNext();
        }
        current.setProductNext(newNode);
        size++;
    }

    public boolean remove(int productId) {
        if (isEmpty()) return false;

        if (head.getProduct().getProductId() == productId) {
            head = head.getProductNext();
            size--;
            return true;
        }

        ProductNode current = head;
        while (current.getProductNext() != null) {
            if (current.getProductNext().getProduct().getProductId() == productId) {
                current.setProductNext(current.getProductNext().getProductNext());
                size--;
                return true;
            }
            current = current.getProductNext();
        }
        return false;
    }

    public Product findById(int productId) {
        ProductNode current = head;
        while (current != null) {
            if (current.getProduct().getProductId() == productId)
                return current.getProduct();
            current = current.getProductNext();
        }
        return null;
    }


    /* Used java utils before but based on sires request we had to create out own List  and Array List */
    //Ai aded in understanding toList  through an sample other directly typed to cement the concept
    public ProductList toList() {
        ProductList result = new ProductList();
        ProductNode current = head;

        while (current != null) {
            result.add(current.getProduct());
            current = current.getProductNext();
        }

        return result;
    }

    public int getSize() { 
        return size;
    }
    public boolean isEmpty() { 
        return head == null; 
    }
}
