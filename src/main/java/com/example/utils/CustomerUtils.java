package com.example.utils;

import java.util.Objects;
import java.time.LocalDate;
import java.security.SecureRandom;
import java.util.Base64; 

public final class CustomerUtils {
    private static final String alphaNum = "abcdefghijklmnopqrstuv0123456789";
    private static final SecureRandom random = new SecureRandom();


    private CustomerUtils(){}

    public static String customerIdGenerator(int length){
        StringBuilder builder = new StringBuilder(length);

        for(int i = 0; i<=length; i++){
            builder.append(alphaNum.charAt(random.nextInt(alphaNum.length())));
        }
        return builder.toString();
    }

}
