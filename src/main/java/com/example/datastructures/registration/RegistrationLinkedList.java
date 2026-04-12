package com.example.datastructures.registration;

import com.example.models.Registration;
import com.example.datastructures.registration.RegistrationList;


public class RegistrationLinkedList {

    private RegistrationNode head;
    private int size;

    public RegistrationLinkedList() {
        head = null;
        size = 0;
    }
    public void add(Registration reg) {
        RegistrationNode newNode = new RegistrationNode(reg);
        newNode.setRegistrationNext(head);
        head = newNode;
        size++;
    }

    public void addToBack(Registration reg) {
        RegistrationNode newNode = new RegistrationNode(reg);

        if (isEmpty()) {
            head = newNode;
            size++;
            return;
        }

        RegistrationNode current = head;

        while (current.getRegistrationNext() != null) {
            current = current.getRegistrationNext();
        }

        current.setRegistrationNext(newNode);
        size++;
    }

    public void addAtIndex(Registration reg, int index) {
        if (index <= 0 || isEmpty()) {
            add(reg);
            return;
        }

        RegistrationNode newNode = new RegistrationNode(reg);
        RegistrationNode current = head;

        for (int i = 0; i < index - 1 && current.getRegistrationNext() != null; i++) {
            current = current.getRegistrationNext();
        }

        newNode.setRegistrationNext(current.getRegistrationNext());
        current.setRegistrationNext(newNode);
        size++;
    }

    public Registration findById(String registrationId) {
        RegistrationNode current = head;

        while (current != null) {
            if (current.getRegistration().getRegistrationId().equals(registrationId)) {
                return current.getRegistration();
            }
            current = current.getRegistrationNext();
        }

        return null;
    }

    public boolean remove(String registrationId) {
        if (isEmpty()) return false;

        if (head.getRegistration().getRegistrationId().equals(registrationId)) {
            head = head.getRegistrationNext();
            size--;
            return true;
        }

        RegistrationNode current = head;

        while (current.getRegistrationNext() != null) {
            if (current.getRegistrationNext().getRegistration()
                    .getRegistrationId().equals(registrationId)) {

                current.setRegistrationNext(
                    current.getRegistrationNext().getRegistrationNext()
                );
                size--;
                return true;
            }

            current = current.getRegistrationNext();
        }

        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}