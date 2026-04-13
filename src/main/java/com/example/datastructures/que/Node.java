package com.example.datastructures.que;

public class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }


    //Accessors
    public T getQueData() {
        return data;
    }

    public Node<T> getQueNext() {
        return next;
    }

    // Mutators
    public void setQueData(T data) {
        this.data = data;
    }

    public void setQueNext(Node<T> next) {
        this.next = next;
    }
}