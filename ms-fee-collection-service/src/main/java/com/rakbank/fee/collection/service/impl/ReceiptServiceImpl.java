package com.rakbank.fee.collection.service.impl;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.entity.Student;
import com.rakbank.fee.collection.repository.ReceiptRepository;
import com.rakbank.fee.collection.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final RestTemplate restTemplate;

    @Value("${api.student.details}")
    private String studentDetailsEndpoint;

    public Receipt collectFee(Receipt receipt) {
        ResponseEntity<Student> response = restTemplate.getForEntity(
                studentDetailsEndpoint + receipt.getStudentId(), Student.class);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException("Student not found");
        }
        return receiptRepository.save(receipt);
    }

    public List<Receipt> getReceiptsByStudentId(String studentId) {
        return receiptRepository.findByStudentId(studentId);
    }
}

