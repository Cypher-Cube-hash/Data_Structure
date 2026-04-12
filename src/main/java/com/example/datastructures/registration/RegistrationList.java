package com.example.datastructures.registration;

import com.example.models.Registration;

public class RegistrationList {
    private Registration[] Registration_values;
    private int size;

    public RegistrationList() {
        Registration_values = new Registration[10];
        size = 0;
    }

    public void add(Registration Registration) {
        if (size == Registration_values.length) {
            resize();
        }
        Registration_values[size++] = Registration;
    }

    private void resize() {
        Registration[] newData = new Registration[Registration_values.length * 2];
        for (int i = 0; i < Registration_values.length; i++) {
            newData[i] = Registration_values[i];
        }
        Registration_values = newData;
    }

    public Registration getIndex(int index) {
        return Registration_values[index];
    }

    public int size() {
        return size;
    }
}
