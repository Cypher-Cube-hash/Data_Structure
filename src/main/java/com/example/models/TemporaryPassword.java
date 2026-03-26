package com.example.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "temporary_password")
public class TemporaryPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temp_pass", nullable = false, unique = true)
    private String pass;

    @Column(name = "duration_time", nullable = false)
    private long durationTime;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    protected TemporaryPassword() {}

    public TemporaryPassword(String pass, long durationTime) {
        this.pass = pass;
        this.durationTime = durationTime;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }


    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public long getDurationTime() {
        return durationTime;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}