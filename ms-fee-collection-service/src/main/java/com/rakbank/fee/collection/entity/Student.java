package com.rakbank.fee.collection.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Student {
    private Long id;
    private String name;
    private String studentId;
    private String grade;
    private String mobileNumber;
    private String schoolName;

}