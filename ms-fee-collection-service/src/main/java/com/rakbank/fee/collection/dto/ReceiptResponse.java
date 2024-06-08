package com.rakbank.fee.collection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ReceiptResponse {
    private Long id;
    private String studentId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String paymentMethod;
    private String grade;
    private String creditCardNumber;
    private String referenceNumber;
    private String transactionId;
}

