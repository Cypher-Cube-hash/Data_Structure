package com.example.models;

import java.util.Objects;
import java.time.LocalDate;
import jakarta.persistence.*;
import com.example.utils.RegistartionUtils;
import com.example.utils.TelephoneConverter;

@Entity
@Table(name = "Registration")
public class Registration {

    @Id
    @Column(name = "regID", unique = true, nullable = false)
    private String registrationId;

    @OneToOne
    @JoinColumn(name = "UserId", nullable = false, unique = true, referencedColumnName = "UserID")
    private User user;

    @OneToOne
    @JoinColumn(name = "AccountID", nullable = false, unique = true, referencedColumnName = "AccountID")
    private Account account;

    @Convert(converter = TelephoneConverter.class)
    @Column(name = "Phone")
    private Telephone phone;

    @Column(nullable = false)
    private LocalDate createAt;

    @Column(nullable = false)
    private LocalDate updatedAt;



    public Registration() {
        this.createAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }



    public Registration(User user, Account account, Telephone phone){
        this.registrationId = RegistartionUtils.registrationIdGenerator(14);
        this.user = user;
        this.account = account;
        this.phone = phone;
        this.createAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Registration(String registrationId, User user, Account account, Telephone phone){
        this.registrationId = registrationId;
        this.user = user;
        this.account = account;
        this.phone = phone;
        this.createAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }


    public Registration(Registration registry){
        this.registrationId = registry.registrationId;
        this.user = registry.user;
        this.account = registry.account;
        this.phone = registry.phone;
        this.createAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
    public String getRegistrationId() {
        return registrationId;
    }

    public User getUser() {
        return user;
    }

    public Account getAccount() {
        return account;
    }

    public Telephone getPhone() {
        return phone;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setPhone(Telephone phone) {
        this.phone = phone;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Registration withRegistrationId(String registrationId) {
        this.registrationId = registrationId;
        return this;
    }

    public Registration withUser(User user) {
        this.user = user;
        return this;
    }

    public Registration withAccount(Account account) {
        this.account = account;
        return this;
    }

    public Registration withPhone(Telephone phone) {
        this.phone = phone;
        return this;
    }

    public Registration withCreateAt(LocalDate createAt) {
        this.createAt = createAt;
        return this;
    }

    public Registration withUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Registration)) return false;
        Registration that = (Registration) o;
        return Objects.equals(registrationId, that.registrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationId);
    }
}