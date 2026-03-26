package com.example.models;

import java.util.Objects;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "authentication")
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String passwordHash;
    
    @Column(nullable = false)
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "UserID")
    private String userId;
    
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "AccountID")
    private String accountId;
    
    @Column(nullable = false)
    private LocalDate createdAt;
    
    @Column(nullable = false)
    private LocalDate updatedAt;

    

    public Authentication() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Authentication(String email, String passwordHash, boolean isActive) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.isActive = isActive;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Authentication(String email, String passwordHash, boolean isActive, String user, String account) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.isActive = isActive;
        this.userId = user;
        this.accountId = account;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Authentication(Long id, String email, String passwordHash, boolean isActive, 
                          String user, String account, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.isActive = isActive;
        this.userId = user;
        this.accountId = account;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Authentication(Authentication auth) {
        this.id = auth.id;
        this.email = auth.email;
        this.passwordHash = auth.passwordHash;
        this.isActive = auth.isActive;
        this.userId = auth.userId;
        this.accountId = auth.accountId;
        this.createdAt = auth.createdAt;
        this.updatedAt = auth.updatedAt;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public String getUser() {
        return userId;
    }

    public String getAccount() {
        return accountId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
        this.updatedAt = LocalDate.now();
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        this.updatedAt = LocalDate.now();
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        this.updatedAt = LocalDate.now();
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    /* public void setUser(User user) {
        this.userId = userId;
        this.updatedAt = LocalDate.now();
    }

    public void setAccount(Account account) {
        this.account = account;
        this.updatedAt = LocalDate.now();
    }

    // Withers
    public Authentication withId(Long id) {
        this.id = id;
        return this;
    }

    public Authentication withEmail(String email) {
        this.email = email;
        this.updatedAt = LocalDate.now();
        return this;
    }

    public Authentication withPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        this.updatedAt = LocalDate.now();
        return this;
    }

    public Authentication withActive(boolean isActive) {
        this.isActive = isActive;
        this.updatedAt = LocalDate.now();
        return this;
    }

    public Authentication withCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Authentication withUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Authentication withUser(User user) {
        this.user = user;
        this.updatedAt = LocalDate.now();
        return this;
    }

    public Authentication withAccount(Account account) {
        this.account = account;
        this.updatedAt = LocalDate.now();
        return this;
    }
 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authentication)) return false;
        Authentication that = (Authentication) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}