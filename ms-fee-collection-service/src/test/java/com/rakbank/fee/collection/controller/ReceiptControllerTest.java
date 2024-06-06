package com.rakbank.fee.collection.controller;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.service.ReceiptService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ReceiptControllerTest {

    @Mock
    private ReceiptService receiptService;

    @InjectMocks
    private ReceiptController receiptController;

    private Receipt receipt;

    @BeforeEach
    public void setUp() {
        receipt = new Receipt();
        receipt.setReceiptId("RC12345");
        receipt.setStudentId("ST12345");
        receipt.setAmount(BigDecimal.valueOf(1500.00));
        receipt.setDate(LocalDate.now());
    }

    @Test
    public void testCollectFee() {
        Mockito.when(receiptService.collectFee(Mockito.any(Receipt.class))).thenReturn(receipt);
        ResponseEntity<Receipt> response = receiptController.collectFee(receipt);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("RC12345", response.getBody().getReceiptId());
    }

    @Test
    public void testGetReceipts() {
        List<Receipt> receipts = Collections.singletonList(receipt);
        Mockito.when(receiptService.getReceiptsByStudentId("ST12345")).thenReturn(receipts);
        ResponseEntity<List<Receipt>> response = receiptController.getReceipts("ST12345");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertFalse(response.getBody().isEmpty());
        Assertions.assertEquals(1, response.getBody().size());
    }
}
