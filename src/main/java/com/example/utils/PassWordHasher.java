package com.example.utils;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public final class PassWordHasher {

    //This prevents an instance of the util from being innitialized
    private PassWordHasher(){};


    public static String hashPassword(String password){

        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
        String hashedPassword = encoder.encode(password);

        return hashedPassword;
    }

    public static boolean verifyPassword(String entered_password, String hashed_password){
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
        return encoder.matches(entered_password, hashed_password);
    }
}
