package com.example.datastructures.temporaryPassword;

import com.example.models.TemporaryPassword;

public class TemporaryPasswordList {
    private TemporaryPassword[] TemporaryPassword_values;
    private int size;

    public TemporaryPasswordList() {
        TemporaryPassword_values = new TemporaryPassword[10];
        size = 0;
    }

    public void add(TemporaryPassword TemporaryPassword) {
        if (size == TemporaryPassword_values.length) {
            resize();
        }
        TemporaryPassword_values[size++] = TemporaryPassword;
    }

    private void resize() {
        TemporaryPassword[] newData = new TemporaryPassword[TemporaryPassword_values.length * 2];
        for (int i = 0; i < TemporaryPassword_values.length; i++) {
            newData[i] = TemporaryPassword_values[i];
        }
        TemporaryPassword_values = newData;
    }

    public TemporaryPassword getIndex(int index) {
        return TemporaryPassword_values[index];
    }

    public int size() {
        return size;
    }
}
