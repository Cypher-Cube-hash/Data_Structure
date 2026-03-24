package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.models.TemporaryPassword;

public interface TemporaryPasswordRepository extends JpaRepository<TemporaryPassword, Integer>{
    
}