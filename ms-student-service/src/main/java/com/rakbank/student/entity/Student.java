package com.rakbank.student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "student_name")
    private String studentName;

    @NotNull
    @Column(name = "student_id", insertable = true,nullable = false)
    private String studentId;
    @NotNull
    @Column(name = "grade")
    private String grade;
    @NotNull
    @Column(name = "mobile_number")
    private String mobileNumber;

    @NotNull
    @Column(name = "school_name")
    private String schoolName;
    @NotNull
    @Column(name = "yearly_fee")
    private BigDecimal yearlyFee;

}
