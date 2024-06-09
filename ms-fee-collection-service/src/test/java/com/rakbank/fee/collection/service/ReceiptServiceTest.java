package com.rakbank.fee.collection.service;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.dto.Student;
import com.rakbank.fee.collection.exceptions.CustomException;
import com.rakbank.fee.collection.repository.ReceiptRepository;
import com.rakbank.fee.collection.dto.ReceiptResponse;
import com.rakbank.fee.collection.service.impl.ReceiptServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReceiptServiceTest {

    @Mock
    private ReceiptRepository receiptRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ReceiptServiceImpl receiptService;

    @Test
    void collectFee_StudentNotFound() {
        when(restTemplate.getForEntity(anyString(), eq(Student.class)))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        Receipt receipt = new Receipt();
        receipt.setStudentId("S12345");
        receipt.setAmount(BigDecimal.valueOf(500));

        assertThrows(CustomException.class, () -> receiptService.collectFee(receipt));

        verify(restTemplate).getForEntity(anyString(), eq(Student.class));
    }

    @Test
    void collectFee_FeePaidFully() {
        when(restTemplate.getForEntity(anyString(), eq(Student.class)))
                .thenReturn(new ResponseEntity<>(new Student("S12345", BigDecimal.valueOf(500)), HttpStatus.OK));

        Receipt receipt = new Receipt();
        receipt.setStudentId("S12345");
        receipt.setAmount(BigDecimal.valueOf(500));

        assertThrows(CustomException.class, () -> receiptService.collectFee(receipt));

        verify(restTemplate).getForEntity(anyString(), eq(Student.class));
    }

    @Test
    void collectFee_Successful() throws CustomException {
        when(restTemplate.getForEntity(anyString(), eq(Student.class)))
                .thenReturn(new ResponseEntity<>(new Student("S12345", BigDecimal.valueOf(1000)), HttpStatus.OK));

        when(receiptRepository.save(any(Receipt.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Receipt receipt = new Receipt(
                1L,
                "S12345",
                "1",
                "TXN123456789",
                BigDecimal.valueOf(1500.00),
                LocalDateTime.of(2024, 6, 8, 10, 30),
                "Credit Card",
                "1234567812345678",
                "REF123456789"
        );

        ReceiptResponse response = receiptService.collectFee(receipt);

        assertNotNull(response);
        assertNotNull(response.getTransactionId());
        assertNotNull(response.getReferenceNumber());
        assertEquals("S12345", response.getStudentId());
        assertEquals(BigDecimal.valueOf(1500.0), response.getAmount());
        assertNotNull(response.getTransactionDate());
        assertNotNull(response.getPaymentMethod());
        assertNotNull(response.getCreditCardNumber());

        verify(restTemplate).getForEntity(anyString(), eq(Student.class));
        verify(receiptRepository, times(1)).save(receipt);
    }

    @Test
    void getReceiptsByStudentId_StudentNotRegistered() {
        when(receiptRepository.findByStudentId("S12345")).thenReturn(new ArrayList<>());

        assertThrows(RuntimeException.class, () -> receiptService.getReceiptsByStudentId("S12345"));

        verify(receiptRepository, times(1)).findByStudentId("S12345");
    }

    @Test
    void getReceiptsByStudentId_Successful() {
        List<Receipt> receipts = new ArrayList<>();
        receipts.add(new Receipt());
        receipts.add(new Receipt());
        when(receiptRepository.findByStudentId("S12345")).thenReturn(receipts);

        List<ReceiptResponse> responseList = receiptService.getReceiptsByStudentId("S12345");

        assertNotNull(responseList);
        assertEquals(2, responseList.size());

        verify(receiptRepository, times(1)).findByStudentId("S12345");
    }
}
