package com.example.models;

import java.util.Objects;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import jakarta.persistence.*;
import com.example.enums.TypeGender;
import com.example.utils.CustomerUtils;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customerId", nullable = false, unique = true)
    private String customerId;

    @OneToOne
    @JoinColumn(name = "UserID", nullable = false, referencedColumnName = "UserID", unique = true)
    private User user;

    @Column(nullable = false, name = "Gender")
    private TypeGender gender;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    public Customer(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Customer(User user, TypeGender gender){
        this.customerId = CustomerUtils.customerIdGenerator(10);
        this.user = user;
        this.gender = gender;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Customer(String cusId, User user, TypeGender gender, LocalDate created, LocalDate upDate){
        this.customerId = cusId;
        this.user = user;
        this.gender = gender;
        this.createdAt = created;
        this.updatedAt = upDate;
    }


    public Customer(Customer customer){
        this.customerId = customer.customerId;
        this.user = customer.user;
        this.gender = customer.gender;
        this.createdAt = customer.createdAt;
        this.updatedAt = customer.updatedAt;
    }




    public String getCustomerId() {
        return customerId;
    }

    public User getUser() {
        return user;
    }

    public TypeGender getGender() {
        return gender;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }


    public String getUserFullName() {
        return user != null ? user.getFullName() : null;
    }

    public String getUserFirstName() {
        return user != null ? user.getFirstName() : null;
    }

    public String getUserLastName() {
        return user != null ? user.getLastName() : null;
    }

    public String getUserEmail() {
        return user != null ? user.getEmail() : null;
    }

    public String getUserId() {
        return user != null ? user.getUserId() : null;
    }

    public Address getUserAddress() {
        return user != null ? user.getAddress() : null;
    }

    // ============ SETTERS ============
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGender(TypeGender gender) {
        this.gender = gender;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }


    public Customer withUser(User newUser) {
        return new Customer(
            this.customerId,
            newUser,
            this.gender,
            this.createdAt,
            LocalDate.now() 
        );
    }



    public Customer withGender(TypeGender newGender) {
        return new Customer(
            this.customerId,
            this.user,
            newGender,
            this.createdAt,
            LocalDate.now() 
        );
    }

    
    public Customer withCustomerId(String newCustomerId) {
        return new Customer(
            newCustomerId,
            this.user,
            this.gender,
            this.createdAt,
            LocalDate.now() 
        );
    }

    
    public Customer withUpdatedAt(LocalDate newUpdatedAt) {
        return new Customer(
            this.customerId,
            this.user,
            this.gender,
            this.createdAt,
            newUpdatedAt
        );
    }

    
    public Customer withUserAndGender(User newUser, TypeGender newGender) {
        return new Customer(
            this.customerId,
            newUser,
            newGender,
            this.createdAt,
            LocalDate.now()
        );
    }

    
    public Customer withNewCustomerId() {
        return new Customer(
            CustomerUtils.customerIdGenerator(10),
            this.user,
            this.gender,
            this.createdAt,
            LocalDate.now()
        );
    }

    
    public Customer withCreatedAt(LocalDate newCreatedAt) {
        return new Customer(
            this.customerId,
            this.user,
            this.gender,
            newCreatedAt,
            LocalDate.now()
        );
    }

    // ============ UTILITY METHODS ============
    
    
    public boolean hasValidUser() {
        return user != null && user.getUserId() != null;
    }

    
    public String getDemographicInfo() {
        if (user == null) return "Unknown";
        return "Gender: " + gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
               Objects.equals(user, customer.user) &&
               gender == customer.gender &&
               Objects.equals(createdAt, customer.createdAt) &&
               Objects.equals(updatedAt, customer.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, user, gender, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Customer{" +
               "customerId='" + customerId + '\'' +
               ", userId=" + (user != null ? user.getUserId() : null) +
               ", userName=" + (user != null ? user.getFullName() : null) +
               ", gender=" + gender +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }
}
