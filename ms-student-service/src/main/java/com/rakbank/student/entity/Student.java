package com.rakbank.student.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Entity representing a student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Student name is mandatory")
    @Schema(description = "Name of the student", example = "John Doe", required = true)
    private String studentName;

    @NotBlank(message = "Student ID is mandatory")
    @Size(min = 5, max = 10, message = "Student ID must be between 5 and 10 characters")
    @Schema(description = "Unique ID of the student", example = "ST12345", required = true)
    private String studentId;

    @NotBlank(message = "Grade is mandatory")
    @Schema(description = "Grade of the student", example = "10", required = true)
    private String grade;

    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    @Schema(description = "Mobile number of the student", example = "1234567890", required = true)
    private String mobileNumber;

    @NotBlank(message = "School name is mandatory")
    @Schema(description = "Name of the school", example = "Springfield High", required = true)
    private String schoolName;

    // Getters and Setters
}
