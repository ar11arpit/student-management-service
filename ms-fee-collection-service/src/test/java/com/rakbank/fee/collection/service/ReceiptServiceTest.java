package com.rakbank.fee.collection.service;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.entity.Student;
import com.rakbank.fee.collection.repository.ReceiptRepository;
import com.rakbank.fee.collection.service.impl.ReceiptServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTest {

    @Mock
    private ReceiptRepository receiptRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ReceiptServiceImpl receiptService;

    @Value("${api.student.details}")
    private String studentDetailsEndpoint = "http://localhost:8080/students/";

    @BeforeEach
    void setUp() {
        // Inject the studentDetailsEndpoint value
        ReflectionTestUtils.setField(receiptService, "studentDetailsEndpoint", studentDetailsEndpoint);
    }

    @Test
    void testCollectFee_Success() {
        // Arrange
        Receipt receipt = new Receipt();
        receipt.setStudentId("1");
        receipt.setAmount(BigDecimal.valueOf(1000.0));

        Student student = new Student();
        student.setId(Long.valueOf("1"));
        ResponseEntity<Student> responseEntity = new ResponseEntity<>(student, HttpStatus.OK);

        when(restTemplate.getForEntity(studentDetailsEndpoint + receipt.getStudentId(), Student.class))
                .thenReturn(responseEntity);
        when(receiptRepository.save(receipt)).thenReturn(receipt);

        // Act
        Receipt savedReceipt = receiptService.collectFee(receipt);

        // Assert
        assertNotNull(savedReceipt);
        assertEquals(receipt.getStudentId(), savedReceipt.getStudentId());
        assertEquals(receipt.getAmount(), savedReceipt.getAmount());
        verify(receiptRepository, times(1)).save(receipt);
    }

    @Test
    void testCollectFee_StudentNotFound() {
        // Arrange
        Receipt receipt = new Receipt();
        receipt.setStudentId("1");
        receipt.setAmount(BigDecimal.valueOf(1000.0));

        ResponseEntity<Student> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        when(restTemplate.getForEntity(studentDetailsEndpoint + receipt.getStudentId(), Student.class))
                .thenReturn(responseEntity);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> receiptService.collectFee(receipt));
        assertEquals("Student not found", exception.getMessage());
        verify(receiptRepository, never()).save(receipt);
    }

    @Test
    void testGetReceiptsByStudentId() {
        // Arrange
        String studentId = "1";
        Receipt receipt = new Receipt();
        receipt.setStudentId(studentId);
        receipt.setAmount(BigDecimal.valueOf(1000.0));
        List<Receipt> receipts = Collections.singletonList(receipt);

        when(receiptRepository.findByStudentId(studentId)).thenReturn(receipts);

        // Act
        List<Receipt> result = receiptService.getReceiptsByStudentId(studentId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(receipt, result.get(0));
        verify(receiptRepository, times(1)).findByStudentId(studentId);
    }
}
