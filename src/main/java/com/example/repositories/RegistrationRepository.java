package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.models.Registration;

public interface RegistrationRepository extends JpaRepository <Registration, String> {
} 