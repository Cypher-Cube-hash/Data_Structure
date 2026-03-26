package com.example.repositories;

import java.util.Optional;

import org.checkerframework.checker.units.qual.t;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.models.TemporaryPassword;

import jakarta.transaction.Transactional;

public interface TemporaryPasswordRepository extends JpaRepository<TemporaryPassword, Integer>{
    /* @Modifying
    @Transactional
    @Query("DELETE FROM TemporaryPassword t WHERE t.expiresAt < CURRENT_TIMESTAMP")
    void deletedExpired();


    
    Optional<TemporaryPassword> findByPass(String pass); */

}