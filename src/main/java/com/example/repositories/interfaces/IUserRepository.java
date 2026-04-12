package com.example.repositories.interfaces;

import com.example.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface IUserRepository extends IRepository <User, String>{

    Optional<User> findByEmail(String email);

    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    boolean existsByEmail(String email);


} 