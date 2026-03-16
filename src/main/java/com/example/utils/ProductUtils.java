package com.example.utils;

import java.util.UUID;

public final class ProductUtils {
    
    private ProductUtils(){};

    public static String skuNumberGenerator(String productName){
        String prefix = productName
                .trim()
                .replaceAll("[^a-zA-Z]", "")
                .toUpperCase();

        prefix = prefix.length() >= 3
                ? prefix.substring(0, 3)
                : prefix;

        String unique = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();

        return prefix + "-" + unique;
    }
}
