package com.rakbank.student.utils;

import com.rakbank.student.entity.Student;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utils {

    private static Set<String> generatedIds = new HashSet<>();

    public static String generateStudentId() {
        String studentId;
        do {
            studentId = generateRandomStudentId();
        } while (!generatedIds.add(studentId)); // Ensure uniqueness by retrying if ID already exists
        return studentId;
    }

    private static String generateRandomStudentId() {
        StringBuilder sb = new StringBuilder();
        sb.append("ST");
        sb.append(generateRandomDigits(8));
        return sb.toString();
    }

    private static String generateRandomDigits(int count) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}

