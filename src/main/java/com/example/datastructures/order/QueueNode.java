package com.example.datastructures.order;

import com.example.models.Order;

public class QueueNode {
    private Order order;
    private QueueNode next;

    public QueueNode(Order order) {
        this.order = order;
        this.next = null;
    }

    public Order getOrder() {
        return order;
    }

    public QueueNode getNext() {
        return next;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }
}
