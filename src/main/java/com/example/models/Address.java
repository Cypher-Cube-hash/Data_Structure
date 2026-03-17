package com.example.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;


    public Address(String addressLine1, String addressLine2, String city, String state, String country){
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Address(){
        this("", "", "", "", "");
    }

    public Address(Address address){
        this(address.addressLine1, address.addressLine2, address.city, address.state, address.country);
    }

    public String getLine1(){
        return this.addressLine1;
    }
    public String getLine2(){
        return this.addressLine2;
    }
    public String getCity(){
        return this.city;
    }
    public String getState(){
        return this.city;
    }
    public String getCountry(){
        return this.city;
    }



    //Not accessors in the traditional way but the same concept
    public Address withLine1(String addressLine1){
        return new Address(addressLine1, this.addressLine2, this.city, this.state, this.country);
    }
    public Address withLine2(String addressLine2){
        return new Address(this.addressLine1, addressLine2, this.city, this.state, this.country);
    }
    public Address withCity(String city){
        return new Address(this.addressLine1, this.addressLine2, city, this.state, this.country);
    }
    public Address withState(String state){
        return new Address(this.addressLine1, this.addressLine2, this.city, state, this.country);
    }
    public Address withCountry(String country){
        return new Address(this.addressLine1, this.addressLine2, this.city, this.state, country);
    }
}

