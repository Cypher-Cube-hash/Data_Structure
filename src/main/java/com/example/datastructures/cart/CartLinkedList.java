package com.example.datastructures.cart;

public class CartLinkedList {

    private CartNode head;
    private int size;

    public CartLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(CartItem item) {
        CartNode existing = findNodeById(item.getProduct().getProductId());
        if (existing != null) {
            existing.getCartItem().setQuantity(
                existing.getCartItem().getQuantity() + item.getQuantity()
            );
            return;
        }
        CartNode newNode = new CartNode(item);
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

        CartNode current = head;
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
        CartNode node = findNodeById(productId);
        if (node == null) return -1;
        int oldQuantity = node.getCartItem().getQuantity();
        node.getCartItem().setQuantity(newQuantity);
        return oldQuantity;
    }

    public boolean contains(int productId) {
        return findNodeById(productId) != null;
    }

    public CartItem findById(int productId) {
        CartNode node = findNodeById(productId);
        return node != null ? node.getCartItem() : null;
    }

    private CartNode findNodeById(int productId) {
        CartNode current = head;
        while (current != null) {
            if (current.getCartItem().getProduct().getProductId() == productId)
                return current;
            current = current.getNext();
        }
        return null;
    }

    public CartNode getHead()  { return head; }
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
}