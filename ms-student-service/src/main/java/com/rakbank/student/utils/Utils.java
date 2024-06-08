package com.rakbank.student.utils;

import com.rakbank.student.entity.Student;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utils {

    private static Set<String> generatedIds = new HashSet<>();

    public static String generateStudentId() {
        String studentId = generateRandomStudentId();
        return studentId;
    }

    private static String generateRandomStudentId() {
        StringBuilder sb = new StringBuilder();
        sb.append("ST");
        sb.append(generateRandomDigits(8));
        return sb.toString();
    }

    private static String generateRandomDigits(int count) {
        final long seed = System.currentTimeMillis();
        final Random rng = new Random(seed);


        long number = rng.nextLong() % 100000000L;
        if (number < 0) {
            number *= -1;
        }
        return number+"";
    }
}

