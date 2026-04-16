package com.example.utils;

import java.security.SecureRandom;

public class IDGenerator {
    private static final String alphaNum = "abcdefghijklmnopqrstuv0123456789";
    private static final SecureRandom random = new SecureRandom();


    private IDGenerator(){}

    public static String idGenerator(int length){
        StringBuilder builder = new StringBuilder(length);

        for(int i = 0; i<=length; i++){
            builder.append(alphaNum.charAt(random.nextInt(alphaNum.length())));
        }
        return builder.toString();
    }

    public static int idGeneratorInt(int length){
        StringBuilder builder = new StringBuilder(length);

        for(int i = 0; i<=length; i++){
            builder.append(random.nextInt(10));
        }
        return Integer.parseInt(builder.toString());
    }
}
