package com.rakbank.fee.collection.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the transaction", example = "1", required = true)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "Student ID is mandatory")
    @Column(nullable = false)
    @Schema(description = "ID of the student involved in the transaction", example = "S12345", required = true)
    private String studentId;

    @NotBlank(message = "Student ID is mandatory")
    @Column(nullable = false)
    @Schema(description = "ID of the student involved in the transaction", example = "S12345", required = true)
    private String grade;


    @Schema(description = "Unique identifier of the transaction", example = "TXN123456789", required = true)
    @JsonIgnore
    private String transactionId;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Amount must be a valid number")
    @Column(nullable = false)
    @Schema(description = "Amount of the transaction", example = "1500.00", required = true)
    private BigDecimal amount;

    @NotNull(message = "Transaction date is mandatory")
    private LocalDateTime transactionDate;

    @NotBlank(message = "Payment method is mandatory")
    @Schema(description = "Method used for the payment", example = "Credit Card", required = true)
    @Column(nullable = false)
    private String paymentMethod;

    @NotBlank(message = "Credit card number is mandatory")
    @Pattern(regexp = "\\d{16}", message = "Credit card number must be 16 digits")
    @Schema(description = "Credit card number used for the payment", example = "1234567812345678", required = true)
    private String creditCardNumber;

    @Schema(description = "Additional remarks about the transaction", example = "Fee payment for semester 1")
    @JsonIgnore
    private String referenceNumber;


}
