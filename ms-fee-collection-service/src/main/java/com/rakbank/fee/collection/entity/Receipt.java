package com.rakbank.fee.collection.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Receipt ID is mandatory")
    @Schema(description = "Unique ID of the receipt", example = "RC12345", required = true)
    private String receiptId;

    @NotBlank(message = "Student ID is mandatory")
    @Schema(description = "ID of the student", example = "ST12345", required = true)
    private String studentId;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    @Schema(description = "Fee amount", example = "1500.00", required = true)
    private BigDecimal amount;

    @NotNull(message = "Date is mandatory")
    @Schema(description = "Date of the receipt", example = "2024-06-04", required = true)
    private LocalDate date;
}
