package com.example.datastructures.user;

import com.example.models.User;
import com.example.datastructures.user.UserList;

public class UserLinkedList {
    private UserNode head;
    private int size;
    

    public UserLinkedList(){head = null; size = 0;}

    public void add(User user){
        UserNode newUser = new UserNode(user);

        newUser.setUserNext(head);
        head = newUser;
        size++;
    }
    public void add_to_back(User user){
        if(isEmpty()){
            add(user);
            return;
        }

        UserNode newUser = new UserNode(user);
        UserNode current = newUser;

        while (current.getUserNext() != null) {
            current = current.getUserNext();
        }
        
        current.setUserNext(newUser);
        size++;
    }

    public void add_at_index(User user, int index){
        if(isEmpty()){
            add(user);
            return;
        }

        UserNode newUser = new UserNode(user);
        UserNode current = newUser;

        for(int i = 0; i < index -1; i++){
            current = current.getUserNext();
        }

        newUser.setUserNext(current.getUserNext());
        current.setUserNext(newUser);
        size++;
    }


    public UserList toList() {
        UserList result = new UserList();
        UserNode current = head;

        while (current != null) {
            result.add(current.getUser());
            current = current.getUserNext();
        }

        return result;
    }

    public UserNode getHead() {
        return head;
    }


    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return head == null;
    }

}
