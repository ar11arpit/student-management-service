package com.rakbank.fee.collection.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
public class Student {
    private Long id;
    private String name;
    private String studentId;
    private String grade;
    private String mobileNumber;
    private String schoolName;
    private BigDecimal yearlyFee;

    public Student(String studentId, BigDecimal yearlyFee) {
        this.studentId = studentId;
        this.yearlyFee = yearlyFee;
    }
}