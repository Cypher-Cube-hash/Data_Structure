package com.example.datastructures.order;

import com.example.datastructures.cart.CartItem;
import com.example.datastructures.product.ProductList;
import com.example.datastructures.product.ProductNode;

public class OrderLinkedList {
     private OrderNode head;
    private int size;

    public OrderLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(CartItem item) {
        OrderNode existing = findNodeById(item.getProduct().getProductId());
        if (existing != null) {
            existing.getCartItem().setQuantity(
                existing.getCartItem().getQuantity() + item.getQuantity()
            );
            return;
        }
        OrderNode newNode = new OrderNode(item);
        newNode.setNext(head);
        head = newNode;
        size++;
    }
    public CartItem remove(int productId) {
        if (isEmpty()) return null;

        if (head.getCartItem().getProduct().getProductId() == productId) {
            CartItem removed = head.getCartItem();
            head = head.getNext();
            size--;
            return removed;
        }

        OrderNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getCartItem().getProduct().getProductId() == productId) {
                CartItem removed = current.getNext().getCartItem();
                current.setNext(current.getNext().getNext());
                size--;
                return removed;
            }
            current = current.getNext();
        }
        return null;
    }

    public int updateQuantity(int productId, int newQuantity) {
        OrderNode node = findNodeById(productId);
        if (node == null) return -1;
        int oldQuantity = node.getCartItem().getQuantity();
        node.getCartItem().setQuantity(newQuantity);
        return oldQuantity;
    }

    public boolean contains(int productId) {
        return findNodeById(productId) != null;
    }

    public OrderNode findById(int productId) {
        OrderNode node = findNodeById(productId);
        return node != null ? node : null;
    }

    private OrderNode findNodeById(int productId) {
        OrderNode current = head;
        while (current != null) {
            if (current.getCartItem().getProduct().getProductId() == productId)
                return current;
            current = current.getNext();
        }
        return null;
    }

    public OrderNode getHead()  { return head; }
    public int getSize(){ 
        return size;
    }
    public boolean isEmpty(){
        return head == null;
    }
    public void clear() {
        head = null;
        size = 0;
    }

    public OrderList toList() {
        OrderList result = new OrderList();
        OrderNode current = head;

        while (current != null) {
            result.add(current.getOrder());
            current = current.getNext();
        }

        return result;
    }

    public int size() {
        return size;
    }
}
