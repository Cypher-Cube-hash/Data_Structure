package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.models.Registration;

public interface RegistrationRepository extends JpaRepository <Registration, String> {
    Optional<Registration> findByUser_UserId(String userId);
    Optional<Registration> findByAccount_AccountId(String accountId);
    boolean existsByUser_UserId(String userId);
} 