package com.example.utils;

public class Stack<E> {
    private Node<E> top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public Node<E> getTop() {
        return top;
    }

    public void setTop(Node<E> top) {
        this.top = top;
    }

    public void push(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        E data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }

    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.getData();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    // @Override
    // public String toString() {
    //     StringBuilder sb = new StringBuilder();
    //     sb.append("Stack{");
    //     Node<E> current = top;
    //     while (current != null) {
    //         sb.append(current.getData()).append(" -> ");
    //         current = current.getNext();
    //     }
    //     sb.append("null}");
    //     return sb.toString();
    // }

    public void stackDisplay() {
        Node<E> current = top;
        if(size == 0) {
            System.out.println("Stack is empty.");
            return;
        }
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }
}
