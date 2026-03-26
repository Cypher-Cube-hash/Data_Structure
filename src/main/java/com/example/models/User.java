package com.example.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import com.example.utils.UserUtils;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "UserID", nullable = false, unique = true)
    private String userId;

    @Column(name="First", length = 25, nullable = false)
    private String firstName;

    @Column(name="Last", length = 25, nullable = false)
    private String lastName;

    @Column(name="Email", nullable = false, unique = true)
    private String email;


    @Column(name="address")
    private String address;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;


    public User(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public User(String first, String last, String email, String address){
        this.userId = UserUtils.userIdGenerator(10);
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.address = address;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // ✅ Full constructor
    public User(String userId, String first, String last, String email, String address,
                LocalDate createdAt, LocalDate updatedAt) {
        this.userId = userId;
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ✅ Copy constructor
    public User(User user) {
        this.userId = user.userId;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.address = user.address;
        this.createdAt = user.createdAt;
        this.updatedAt = user.updatedAt;
    }

    // ✅ Getters
    public String getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public LocalDate getCreatedAt() { return createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }

    // ✅ Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }

    // ✅ Wither
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