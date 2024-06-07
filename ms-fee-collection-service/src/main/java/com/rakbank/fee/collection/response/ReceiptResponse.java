package com.rakbank.fee.collection.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReceiptResponse {
    private Long id;
    private String studentId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String paymentMethod;
    private String creditCardNumber;
    private String referenceNumber;
    private String transactionId;
}

