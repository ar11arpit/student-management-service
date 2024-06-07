package com.rakbank.fee.collection.utils;

import java.util.UUID;

public class Utils {
    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    public static String generateReferenceId() {
        long timestamp = System.currentTimeMillis();
        int randomNumber = (int)(Math.random() * 1000); // Generating a random number between 0 and 999
        return "REF-" + timestamp + "-" + randomNumber;
    }
}
