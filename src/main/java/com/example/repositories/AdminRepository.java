package com.example.repositories;

import com.example.models.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository <Admin, String>{
    Optional<Admin> findByUser_UserId(String userId);
    
}
