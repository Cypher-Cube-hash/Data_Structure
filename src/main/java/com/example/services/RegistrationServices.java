package com.example.services;

import com.example.models.Registration;
import com.example.models.Address;
import com.example.repositories.RegistrationRepository;

import com.example.models.Account;
import com.example.models.Telephone;
import com.example.models.TemporaryPassword;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.utils.Emailer;
import com.example.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import com.example.utils.Emailer;
import com.example.utils.OTPUtil;

@Service
public class RegistrationServices {
    private final RegistrationRepository repo;
    private final UserRepository user;
    private final AccountRepository account;




    public RegistrationServices(RegistrationRepository repo, UserRepository user, AccountRepository account){
        this.repo = repo;
        this.user = user;
        this.account = account;
    }

    public List<Registration> getAllUsers(){
        return repo.findAll();
    }

   
    //This function is for adding registered Users
    @Transactional
    public Registration addRegisteredUser(String firstName, String lastName, String email, Address address, Telephone telephone){
        
        if(user.existsByEmail(email)){
            throw new IllegalArgumentException("Email already exists");
        }

        String otp = OTPUtil.generateOTP();
        
        //This is for the user Creation.
        User newUser = new User(firstName, lastName, email, address);
        user.save(newUser);
        //This is for the Account Creation.
        Account newAccount = new Account(telephone);
        account.save(newAccount);
        //This is for the general creation of the registered user
        Registration registration = new Registration(newUser, newAccount, telephone);
        repo.save(registration);


        TemporaryPassword temp = new TemporaryPassword(otp, System.currentTimeMillis() + 300000);
        Emailer.sendEmail(email, otp);
            
        return registration;
    }






}
