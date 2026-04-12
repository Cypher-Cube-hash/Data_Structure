package com.example.utils;

import java.util.List;

// import com.example.models.CartItem;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public LinkedList(Node<T> head) {
        this.head = head;
        this.tail = head;

    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setHead(Node<T> head){
        this.head = head;
    }

    public void setTail(Node<T> tail){
        this.tail = tail;
    }

    public void insertAtFront(T data) {
        Node<T> node = new Node<>(data);
        node.setNext(head);
        head  = node;

        if(tail == null) {
            tail = node;
        }
    }

    public void insertAtEnd(T data) {
        Node<T> node = new Node<>(data);

        if(head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public void remove(T data) {
        Node<T> current = head;
        Node<T> prev = null;

        while (current != null) {

            if (current.getData().equals(data)) {

                // Case 1: Removing head
                if (prev == null) {
                    head = current.getNext();
                } 
                // Case 2: Removing middle or last
                else {
                    prev.setNext(current.getNext());
                }

                // Case 3: If it's the tail
                if (current == tail) {
                    tail = prev;
                }

                return; // stop after removing
            }

            prev = current;
            current = current.getNext();
        }
    }

    public List<T> toList() {
        List<T> list = new java.util.ArrayList<>();
        Node<T> current = head;

        while(current != null) {
            list.add(current.getData());
            current = current.getNext();
        }

        return list;
    }

    public void showList() {
        Node<T> current = head;

        while(current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
     }

    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }



}
