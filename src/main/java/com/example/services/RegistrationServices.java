package com.example.services;

import com.example.models.Registration;
import com.example.models.Account;
import com.example.models.Telephone;
import com.example.models.TemporaryPassword;
import com.example.models.User;
import com.example.repositories.interfaces.IRegistrationRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.services.TemporaryPasswordService;


import java.util.List;
import com.example.utils.Emailer;
import com.example.utils.OTPUtil;

@Service
public class RegistrationServices {
    private final IRegistrationRepository repo;

    public RegistrationServices(IRegistrationRepository repo){
        this.repo = repo;
        this.user = user;
        this.account = account;
        this.tempPassword = tempPassword;
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

        String fullAddress = address.getLine1() + ", " +
                     address.getLine2() + ", " +
                     address.getCity() + ", " +
                     address.getState() + ", " +
                     address.getCountry();

        User newUser = new User(firstName, lastName, email, fullAddress);
        //This is for the user Creation.
        user.save(newUser);
        //This is for the Account Creation.
        Account newAccount = new Account(telephone);
        account.save(newAccount);
        //This is for the general creation of the registered user
        Registration registration = new Registration(newUser, newAccount, telephone);
        repo.save(registration);


        TemporaryPassword temp = new TemporaryPassword(otp, 5);
        tempPassword.save(temp);

        System.out.println("About to send email to: " + email);
        Emailer.sendEmail(email, otp);
        System.out.println("Email sent successfully");
            
        return registration;
    }






}
