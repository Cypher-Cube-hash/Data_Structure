package com.example.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "temporary_password")
public class TemporaryPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temp_pass", nullable = false, unique = true)
    private String pass;

    /* @Column(name = "duration_time", nullable = false) */
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    protected TemporaryPassword() {}

    public TemporaryPassword(String pass) {
        this.pass = pass;
        this.expiresAt = LocalDateTime.now().plusMinutes(5);
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(this.expiresAt);
    }
}
