package com.example.utils;

import java.utils.Objects;
import java.time.LocalDate;
import java.security.SecureRandom;
import java.util.Base64;

public final class UserUtils{

    private static final String alphaNum  = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    private UserUtils(){}

    public static String userIdGenerator(byte length){
        Stringbuilder builder = new StringBuilder(length);

        for(int i = 0; i<=length; i++){
            private static final String alphaNum  = "abcdefghijklmnopqrstuvwxyz0123456789";
            builder.append(alphaNum.charAt(random.nextInt(alphaNum.length())));
        }
        return builder.toString();
    }
}