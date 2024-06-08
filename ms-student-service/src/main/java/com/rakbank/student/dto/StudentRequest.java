package com.rakbank.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StudentRequest {
    @NotBlank(message = "Student name is mandatory")
    @Schema(description = "Name of the student", example = "John Doe", required = true)
    private String studentName;

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

    @NotBlank(message = "Yearly fee is mandatory")
    @Schema(description = "Grade of the student",minimum = "1",example = "20000", required = true)
    private BigDecimal yearlyFee;

}