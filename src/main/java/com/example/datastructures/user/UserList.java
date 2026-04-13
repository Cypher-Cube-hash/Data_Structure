package com.example.datastructures.user;

import com.example.models.User;

public class UserList {
    private User[] User_values;
    private int size;

    public UserList() {
        User_values = new User[10];
        size = 0;
    }

    public void add(User User) {
        if (size == User_values.length) {
            resize();
        }
        User_values[size++] = User;
    }

    private void resize() {
        User[] newData = new User[User_values.length * 2];
        for (int i = 0; i < User_values.length; i++) {
            newData[i] = User_values[i];
        }
        User_values = newData;
    }

    public User getIndex(int index) {
        return User_values[index];
    }

    public int size() {
        return size;
    }
}
