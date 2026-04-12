package com.example.services;

import com.example.models.User;
import com.example.models.Address;
// import com.example.repositories.UserRepository;
import com.example.repositories.interfaces.IUserRepository;

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
    public User registerUser(String first, String last, String email, Address address){
        if(repo.existsByEmail(email)){
            throw new IllegalArgumentException("Email already exists");
        }

        Address fullAddress = new Address(
            address.getLine1(),
            address.getLine2(),
            address.getCity(),
            address.getState(),
            address.getCountry()
        );

        User user = new User(first, last, email, fullAddress);
        repo.save(user);
        return user;
    }

    public User registerUser(User user){
        repo.save(user);
        return user;
    }


}
