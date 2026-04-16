package com.example.datastructures.order;

import org.springframework.stereotype.Component;

import com.example.models.Order;

@Component
public class OrderQueue {
    private QueueNode front;
    private QueueNode rear;

    public void enqueue(Order order) {
        QueueNode node = new QueueNode(order);

        if (rear == null) {
            front = rear = node;
            return;
        }

        rear.setNext(node);
        rear = node;
    }

    public Order dequeue() {
        if (front == null) return null;

        Order order = front.getOrder();
        front = front.getNext();

        if (front == null) rear = null;

        return order;
    }

    public boolean isEmpty(){
        return front == null;
    }

    public OrderList toList() {
        OrderList result = new OrderList();
        QueueNode current = front;

        while (current != null) {
            result.add(current.getOrder());
            current = current.getNext();
        }

        return result;
    }
}
