package com.example.models;

import java.util.Objects;
import java.time.LocalDate;
import jakarta.persistence.*;
import com.example.utils.UserUtils;

@Entity
@Table(
        name = "users"
)
public class User{

    @Id
    @Column(name = "UserID", nullable = false, unique = true, length=10)
    private String userId;

    @Column(name="First", length = 25, nullable = false)
    private String firstName;

    @Column(name="Last", length = 25, nullable = false)
    private String lastName;

    @Column(name="Email", nullable = false, unique = true)
    private String email;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;


    public User(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public User(String first, String last, String email){ //, Address address
        this.userId = UserUtils.userIdGenerator(10);
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        // this.address = address;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // Added missing constructor for withFirstName method
    public User(String userId, String first, String last, String email, Address address, LocalDate createdAt, LocalDate updatedAt) {
        this.userId = userId;
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(User user) {
        this.userId = user.userId;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.address = user.address;
        this.createdAt = user.createdAt;
        this.updatedAt = user.updatedAt;
    }

    //Accessor's (Getters)
    public String getUserId() {return userId;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getFullName() {return firstName + " " + lastName;}
    public String getEmail() {return email;}
    public Address getAddress() {return address;}
    public LocalDate getCreatedAt() {return createdAt;}
    public LocalDate getUpdatedAt() {return updatedAt;}

    // Mutators(Setters)
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setEmail(String email) {this.email = email;}

    public void setAddress(Address address) {this.address = address;}

    public void setUpdatedAt(LocalDate updatedAt) {this.updatedAt = updatedAt;}

    //Mutators (Withers) - Fixed
    public User withFirstName(String first){
        return new User(
            this.userId,
            first, 
            this.lastName, 
            this.email, 
            this.address,
            this.createdAt,
            LocalDate.now() 
        );
    }
}