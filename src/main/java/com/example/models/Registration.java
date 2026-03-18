package com.example.models;

import java.util.Objects;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "Registration")
public class Registration {
    @Id
    @Column(name = "regID", unique = true, nullable = false)
    public String registrationId;
    @OneToOne
    @JoinColumn(name = "UserId", nullable = false, unique = true, referencedColumnName = "UserID")
    public User user;
    
    

}
