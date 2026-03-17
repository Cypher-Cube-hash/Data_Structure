package com.example.models;

import java.util.Objects;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(
        name = "users"
)
public class User{

    @Id
    @Column(name = "UserID", nullable = false, unique = true);
    private String userID;
    @Column(name="First", length = 25, nullable = false;);
    private String firstName;
    @Column(name="Last", length = 25, nullable = false);
    private String lastName;
    @Column(name="Email", nullable = false, unique = true);
    private String email;
    @Embedded
    private Address address;
    @Column(nullable = false);
    private LocalDate createdAt;
    @Column(nullable = false);
    private LocalDate updateAt;


    public User{
        this.createdAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public User(String first, String last, String email, Address address){
        this.userId = UserUtils.userIdGenerator(10);
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.address = address;
    }

    public User(User user) {
        this.userId = user.userId;
        this.firstName = user.first;
        this.lastName = user.last;
        this.email = user.email;
        this.address = user.address;
        this.createdAt = user.product.createdAt;
        this.updatedAt = user.product.updatedAt;
    }



}