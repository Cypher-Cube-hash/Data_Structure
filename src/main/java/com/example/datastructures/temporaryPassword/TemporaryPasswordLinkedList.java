package com.example.datastructures.temporaryPassword;

import com.example.models.TemporaryPassword;


public class TemporaryPasswordLinkedList {
    private TemporaryPasswordNode head;
    private int size;

    public TemporaryPasswordLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(TemporaryPassword tempPass) {
        TemporaryPasswordNode newNode = new TemporaryPasswordNode(tempPass);
        newNode.setTemporaryPasswordNext(head);
        head = newNode;
        size++;
    }

    public void addToBack(TemporaryPassword tempPass) {
        if (isEmpty()) { add(tempPass); return; }
        TemporaryPasswordNode newNode = new TemporaryPasswordNode(tempPass);
        TemporaryPasswordNode current = head;
        while (current.getTemporaryPasswordNext() != null) {
            current = current.getTemporaryPasswordNext();
        }
        current.setTemporaryPasswordNext(newNode);
        size++;
    }

    public boolean remove(int tempPassId) {
        if (isEmpty()) return false;

        if (head.getTemporaryPassword().getId() == tempPassId) {
            head = head.getTemporaryPasswordNext();
            size--;
            return true;
        }

        TemporaryPasswordNode current = head;
        while (current.getTemporaryPasswordNext() != null) {
            if (current.getTemporaryPasswordNext().getTemporaryPassword().getId() == tempPassId) {
                current.setTemporaryPasswordNext(current.getTemporaryPasswordNext().getTemporaryPasswordNext());
                size--;
                return true;
            }
            current = current.getTemporaryPasswordNext();
        }
        return false;
    }

    public TemporaryPassword findById(int tempPassId) {
        TemporaryPasswordNode current = head;
        while (current != null) {
            if (current.getTemporaryPassword().getId() == tempPassId)
                return current.getTemporaryPassword();
            current = current.getTemporaryPasswordNext();
        }
        return null;
    }

    public TemporaryPasswordList toList() {
        TemporaryPasswordList result = new TemporaryPasswordList();
        TemporaryPasswordNode current = head;

        while (current != null) {
            result.add(current.getTemporaryPassword());
            current = current.getTemporaryPasswordNext();
        }

        return result;
    }

    public int getSize() { 
        return size;
    }
    public boolean isEmpty() { 
        return head == null; 
    }
}
