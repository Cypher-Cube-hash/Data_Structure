package com.example.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.models.Admin;
import com.example.repositories.AdminRepository;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.models.Authentication;
import com.example.repositories.AuthenticationRepository;

import com.example.enums.TypeGender;
import com.example.utils.PassWordHasher;

//I used seeders in previouse application so sthe understanding of it is simple.

@Component
public class Seeder implements ApplicationRunner{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationRepository authRepository;


    @Override
    public void run(ApplicationArguments arg){

        User userAdmin = new User("Devin", "Smith", "devinsmith@outlook.com", "Beverly heights Beverly P.O St Thomas");
        Admin adminOne = new Admin(userAdmin, TypeGender.MALE);
        Authentication adminAuth = new Authentication("devinsmith@outlook.com", 
        PassWordHasher.hashPassword("Password12!"), 
        true, 
        userAdmin.getUserId());
        

        //UserId: haagxvgaqfq
        //AdminId: haggxvgaafa
        //Gender: TypeGender.MALE
        //First: Devin
        //Last: Smith
        //Email: devinsmith@outlook.com
        // Address: Beverly heights Beverly P.O St Thomas
        //Password: Password12!

        //Just realized me making the userIds and AdminId isnt needed, going on to the Admin Page creation

        if(adminRepository.count() == 0){
            //Made a mistake here, the admin was created before the user so I went ahead and adjusted accordingly;
            userRepository.save(userAdmin);
            adminRepository.save(adminOne);
            authRepository.save(adminAuth);
        }
    }
}
