package com.example.utils;

public interface List<T> {
    void add(T data);
    void remove(T data);
    T get(int index);
    int size();
    boolean isEmpty();
}
