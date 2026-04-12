package com.example.services;

import com.example.models.Registration;
import com.example.models.Address;
import com.example.repositories.RegistrationRepository;
import com.example.repositories.TemporaryPasswordRepository;
import com.example.models.Account;
import com.example.models.Telephone;
import com.example.models.TemporaryPassword;
import com.example.models.User;
import com.example.models.Registration;
import com.example.datastructures.registration.RegistrationLinkedList;
import com.example.datastructures.registration.RegistrationList;
import com.example.repositories.UserRepository;
import com.example.utils.Emailer;
import com.example.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.services.TemporaryPasswordService;
import com.example.datastructures.registration.RegistrationLinkedList;


import com.example.utils.Emailer;
import com.example.utils.OTPUtil;

@Service
public class RegistrationServices {
    private final RegistrationRepository repo;
    private final UserRepository user;
    private final AccountRepository account;
    private final TemporaryPasswordRepository tempPassword;




    public RegistrationServices(RegistrationRepository repo, UserRepository user, AccountRepository account, TemporaryPasswordRepository tempPassword){
        this.repo = repo;
        this.user = user;
        this.account = account;
        this.tempPassword = tempPassword;
    }

    public RegistrationList getAllUsers() {

        RegistrationList customList = new RegistrationList();

        for (Registration reg : repo.findAll()) {
            customList.add(reg);
        }
        return customList;
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
        user.save(newUser);
        Account newAccount = new Account(telephone);
        account.save(newAccount);
        Registration registration = new Registration(newUser, newAccount, telephone);
        repo.save(registration);


        TemporaryPassword temp = new TemporaryPassword(otp);
        tempPassword.save(temp);

        System.out.println("About to send email to: " + email);
        Emailer.sendEmail(email, otp);
        System.out.println("Email sent successfully");
            
        return registration;
    }






}
