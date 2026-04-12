package com.example.datastructures.cart;

public class CartStack<T> {

    private Object[] data;
    private int top;

    private static final int INITIAL_CAPACITY = 10;

    public CartStack() {
        data = new Object[INITIAL_CAPACITY];
        top  = -1;
    }

    public void push(T item) {
        if (top + 1 == data.length) resize();
        data[++top] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) return null;
        T item     = (T) data[top];
        data[top--] = null;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) return null;
        return (T) data[top];
    }

    // ── State ──────────────────────────────────────────────────────────────

    public boolean isEmpty(){
        return top == -1;
    }
    public int size(){
        return top + 1;
    }

    public void clear(){
        for (int i = 0; i <= top; i++) data[i] = null;
        top = -1;
    }
    
    private void resize() {
        Object[] bigger = new Object[data.length * 2];
        for (int i = 0; i < data.length; i++) bigger[i] = data[i];
        data = bigger;
    }
}