package com.rakbank.fee.collection.controller;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.dto.ReceiptResponse;
import com.rakbank.fee.collection.service.ReceiptService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptControllerTest {

    @Mock
    private ReceiptService receiptService;

    @InjectMocks
    private ReceiptController receiptController;

    @Test
    public void testCollectFee() {
        Receipt receipt = new Receipt();
        receipt.setStudentId("ST12345");
        receipt.setAmount(BigDecimal.valueOf(1500.00));
        receipt.setTransactionDate(LocalDateTime.now());

        ReceiptResponse savedReceipt = new ReceiptResponse();
        savedReceipt.setReferenceNumber("RC12345");
        savedReceipt.setStudentId("ST12345");
        savedReceipt.setAmount(BigDecimal.valueOf(1500.00));
        savedReceipt.setTransactionDate(LocalDateTime.now());

        when(receiptService.collectFee(any(Receipt.class))).thenReturn(savedReceipt);

        ResponseEntity<ReceiptResponse> response = receiptController.collectFee(receipt);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedReceipt, response.getBody());

        verify(receiptService, times(1)).collectFee(any(Receipt.class));
    }

    @Test
    public void testGetReceipts() {
        String studentId = "ST12345";

        ReceiptResponse receipt = new ReceiptResponse();
        receipt.setReferenceNumber("RC12345");
        receipt.setStudentId("ST12345");
        receipt.setAmount(BigDecimal.valueOf(1500.00));
        receipt.setTransactionDate(LocalDateTime.now());

        List<ReceiptResponse> receipts = Collections.singletonList(receipt);

        when(receiptService.getReceiptsByStudentId(studentId)).thenReturn(receipts);

        ResponseEntity<List<ReceiptResponse>> response = receiptController.getReceipts(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(receipts, response.getBody());

        verify(receiptService, times(1)).getReceiptsByStudentId(studentId);
    }
}
