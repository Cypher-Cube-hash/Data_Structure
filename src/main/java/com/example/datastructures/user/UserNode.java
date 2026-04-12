package com.example.datastructures.user;

import com.example.models.User;

public class UserNode {
    private User user_entity;
    private UserNode userNext;

    public UserNode(User user){
        this.user_entity = user;
        this.userNext = null;
    }

    //Accessors
    public User getUser(){
        return this.user_entity;
    }
    public UserNode getUserNext(){
        return this.userNext;
    }
    //Mutators

    public void setUser(User user){
        this.user_entity = user;
    }
    public void setUserNext(UserNode next){
        this.userNext = next;
    }
}
