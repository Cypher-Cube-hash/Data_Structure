package com.example.datastructures.order;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Order;

public class OrderList {
        private Order[] order_values;
    private int size;

    public OrderList() {
        order_values = new Order[10];
        size = 0;
    }

    public void add(Order order ) {
        if (size == order_values.length) {
            resize();
        }
        order_values[size++] = order;
    }

    private void resize() {
        Order[] newData = new Order[order_values.length * 2];
        for (int i = 0; i < order_values.length; i++) {
            newData[i] = order_values[i];
        }
        order_values = newData;
    }

    public Order getIndex(int index) {
        return order_values[index];
    }

    public int size() {
        return size;
    }

    public List<Order> toJavaList() {
    List<Order> list = new ArrayList<>();

    for (int i = 0; i < size; i++) {
        list.add(order_values[i]);
    }

    return list;
}
}
