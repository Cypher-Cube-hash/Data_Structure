package com.example.repositories;

import com.example.models.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
    boolean existsByEmail(String email);
}
