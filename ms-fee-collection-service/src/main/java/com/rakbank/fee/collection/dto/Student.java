package com.rakbank.fee.collection.dto;

import lombok.Data;

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