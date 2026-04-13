package com.example.datastructures.que;

public class Orderque<T> {

    private Node<T> front;
    private Node<T> rear;

    public void enque(T data) {
        Node<T> newNode = new Node<>(data);

        if (rear == null) {
            front = rear = newNode;
            return;
        }

        rear.setQueNext(newNode);
        rear = newNode;
    }

    public T dequeue() {
        if (front == null) return null;

        T data = front.getQueData();
        front = front.getQueNext();

        if (front == null) {
            rear = null;
        }

        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public T peek() {
        if (front == null) return null;
        return front.getQueData();
    }
}