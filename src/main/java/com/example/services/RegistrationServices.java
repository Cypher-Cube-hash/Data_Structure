package com.example.services;

import com.example.models.Registration;
import com.example.models.Account;
import com.example.models.Telephone;
import com.example.models.User;
import com.example.repositories.interfaces.IRegistrationRepository;

import org.springframework.stereotype.Service;
import java.util.List;

public class RegistrationServices {
    private final IRegistrationRepository repo;

    public RegistrationServices(IRegistrationRepository repo){
        this.repo = repo;
    }

    public List<Registration> getAllUsers(){
        return repo.findAll();
    }

   /*  public Registration addRegistration(User user, Account account, Telephone phone){

    } */
}
