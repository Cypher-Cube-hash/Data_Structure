package com.example.utils;

import java.security.SecureRandom;

public final class AccountUtils {
    private AccountUtils(){}

    private static final String alphaNum = "abcdefghijklmnopqrstuv0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String accountIdGenerator(int length){
        StringBuilder builder = new StringBuilder(length);

        for(int i = 0; i<=length; i++){
            builder.append(alphaNum.charAt(random.nextInt(alphaNum.length())));
        }
        return builder.toString();
    }
}
