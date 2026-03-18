package com.example.services;

import com.example.models.User;
import com.example.models.Address;
import com.example.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServices {
    private final UserRepository repo;

    public UserServices(UserRepository repo){
        this.repo = repo;
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    //Registers the user
    public User registerUser(String first, String last, String email, Address address){
        if(repo.existsByEmail(email)){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User(first, last, email, address);
        return repo.save(user);
    }


}
