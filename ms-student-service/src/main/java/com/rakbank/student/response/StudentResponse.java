package com.rakbank.student.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.awt.geom.QuadCurve2D;
import java.math.BigDecimal;

@Data
public class StudentResponse {

    private Long id;

    @Schema(description = "Name of the student")
    private String studentName;

    @Schema(description = "Unique ID of the student")
    private String studentId;


    private String grade;


    @Schema(description = "Mobile number of the student")
    private String mobileNumber;

    @Schema(description = "Name of the school")
    private String schoolName;

    @Schema(description = "Grade of the student")
    private BigDecimal yearlyFee;}
