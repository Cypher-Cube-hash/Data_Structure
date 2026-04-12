package com.example.datastructures.registration;

import com.example.models.Registration;

public class RegistrationNode {
    private Registration registration_sample;
    private RegistrationNode registrationNext;

    public RegistrationNode(Registration user){
        this.registration_sample = user;
        this.registrationNext = null;
    }

    //Accessors
    public Registration getRegistration(){
        return this.registration_sample;
    }
    public RegistrationNode getRegistrationNext(){
        return this.registrationNext;
    }
    //Mutators

    public void setRegistration(Registration user){
        this.registration_sample = user;
    }
    public void setRegistrationNext(RegistrationNode next){
        this.registrationNext = next;
    }
}
