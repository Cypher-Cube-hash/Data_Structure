package com.example.repositories;

import com.example.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import com.example.datastructures.user.UserLinkedList;


public interface UserRepository extends JpaRepository <User, String>{

    Optional<User> findByEmail(String email);
    Optional<User> findByUserId(String userId);

    UserLinkedList findByFirstName(String firstName);
    UserLinkedList findByLastName(String lastName);
    boolean existsByEmail(String email);
    
} 