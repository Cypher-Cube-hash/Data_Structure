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
    @Column(name = "UserID", nullable = false, unique = true)
    private String userID;
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


    public String getUserId() {return userId;}
    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getFullName() {return firstName + " " + lastName;}

    public String getEmail() {return email;}

    public Address getAddress() {return address;}

    public LocalDate getCreatedAt() {return createdAt;}

    public LocalDate getUpdatedAt() {return updatedAt;}

    // ============= SETTERS =============
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public void setEmail(String email) {this.email = email;}

    public void setAddress(Address address) {this.address = address;}

    public void setUpdatedAt(LocalDate updatedAt) {this.updatedAt = updatedAt;}



    // ============= WITH METHODS (Fluent API for immutability) =============
    public User withFirstName(String firstName) {
        User newUser = new User(this);
        newUser.firstName = firstName;
        return newUser;
    }

    public User withLastName(String lastName) {
        User newUser = new User(this);
        newUser.lastName = lastName;
        return newUser;
    }

    public User withEmail(String email) {
        User newUser = new User(this);
        newUser.email = email;
        return newUser;
    }

    public User withAddress(Address address) {
        User newUser = new User(this);
        newUser.address = address;
        return newUser;
    }

    public User withUpdatedAt(LocalDate updatedAt) {
        User newUser = new User(this);
        newUser.updatedAt = updatedAt;
        return newUser;
    }

    // ============= UTILITY METHODS =============
    public void updateTimestamp() {
        this.updatedAt = LocalDate.now();
    }

    // ============= EQUALS, HASHCODE, TOSTRING =============
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email);
    }

    @Override
    public String toString() {
        return String.format("User{userId='%s', name='%s %s', email='%s'}",
                userId, firstName, lastName, email);
    }

}