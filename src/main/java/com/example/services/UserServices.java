package com.example.services;

import com.example.models.User;
import com.example.repositories.interfaces.IUserRepository;
import com.example.models.Address;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServices {
    private final IUserRepository repo;

    public UserServices(IUserRepository repo){
        this.repo = repo;
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    //Registers the user
    public User registerUser(String first, String last, String email){ //, Address address
        if(repo.existsByEmail(email)){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User(first, last, email); //, address
        repo.save(user);
        return user;
    }


}
