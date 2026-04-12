package com.example.utils;

public class Node<E> {
    private E data;
    private Node<E> next;
    private Node<E> left;
    private Node<E> right;

    public Node() {
        this.data = null;
        this.next = null;
        this.left = null;
        this.right = null;
    }

    public Node(E data) {
        this.data = data;
        this.next = null;
        this.left = null;
        this.right = null;
    }

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
        this.left = null;
        this.right = null;
    }

    public Node(Node<E> next) {
        this.data = next.data;
        this.next = next.next;
        this.left = null;
        this.right = null;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getLeft() {
        return left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
