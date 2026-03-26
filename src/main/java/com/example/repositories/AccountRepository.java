package com.example.repositories;

import com.example.models.Account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, String>{
    /* Optional<Account> findBy */

    boolean existsByActId(String id);
}
