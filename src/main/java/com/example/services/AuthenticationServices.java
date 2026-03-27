package com.example.services;

import com.example.models.Authentication;
import com.example.repositories.AuthenticationRepository;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.models.Account;
import com.example.repositories.AccountRepository;
import com.example.models.Registration;
import com.example.repositories.RegistrationRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {
    private final AuthenticationRepository auth;
    private final UserRepository user;
    private final AccountRepository account;
    private final RegistrationRepository registered;

    public AuthenticationServices(
        AuthenticationRepository authRepo,
        UserRepository userRepo,
        AccountRepository account,
        RegistrationRepository registered
    ){
        this.auth = authRepo;
        this.user = userRepo;
        this.account = account;
        this.registered = registered;
    }

    @Transactional
    public Authentication addAuthentication(String email, String password, boolean active){

        if(auth.existsByEmail(email)){
            throw new IllegalArgumentException("This email already exists.");
        }

        //Since the Auth table will be generated when a user creates the password we want to grab the id to ensure that they have actually
        //dont the registration
        Optional<User> existing_person = user.findByEmail(email);
        User user_exist = null;
        if(existing_person.isPresent()){
            user_exist = existing_person.get();
        }

        Optional<Registration> registered_or_not = registered.findByUser_UserId(user_exist.getUserId());
        Registration isRegistered = null;
        if(registered_or_not.isPresent()){
            isRegistered = registered_or_not.get();
        }

        

        Authentication authUser = new Authentication(email, password, active, user_exist.getUserId(), isRegistered.getRegistrationId());
        auth.save(authUser);

        return authUser;
    }
}
