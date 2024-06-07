package com.rakbank.fee.collection.service.impl;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.entity.Student;
import com.rakbank.fee.collection.repository.ReceiptRepository;
import com.rakbank.fee.collection.response.ReceiptResponse;
import com.rakbank.fee.collection.service.ReceiptService;
import com.rakbank.fee.collection.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final RestTemplate restTemplate;

    @Value("${api.student.details}")
    private String studentDetailsEndpoint;

    public ReceiptResponse collectFee(Receipt receipt) {
        ResponseEntity<Student> response = restTemplate.getForEntity(
                studentDetailsEndpoint + receipt.getStudentId(), Student.class);
        Student student = response.getBody();
        if (response.getBody()==null) {
            throw new RuntimeException("Student not found");
        }
        if(student.getYearlyFee().compareTo(receipt.getAmount())==0){
            throw new RuntimeException("Fees is paid fully for the year");
        }

        receipt.setTransactionId(Utils.generateTransactionId());
        receipt.setReferenceNumber(Utils.generateReferenceId());
        return mapToResponse(receiptRepository.save(receipt));
    }

    public List<ReceiptResponse> getReceiptsByStudentId(String studentId) {
         List<Receipt> receipts =receiptRepository.findByStudentId(studentId);
         if (CollectionUtils.isEmpty(receipts)){
             throw new RuntimeException("Student is not registered");
         }

         return receipts.stream().map(receipt -> mapToResponse(receipt)).collect(Collectors.toList());
    }

    public ReceiptResponse mapToResponse(Receipt receipt) {
        ReceiptResponse response = new ReceiptResponse();
        response.setId(receipt.getId());
        response.setStudentId(receipt.getStudentId());
        response.setAmount(receipt.getAmount());
        response.setTransactionDate(receipt.getTransactionDate());
        response.setPaymentMethod(receipt.getPaymentMethod());
        response.setCreditCardNumber(receipt.getCreditCardNumber());
        response.setReferenceNumber(receipt.getReferenceNumber());
        response.setTransactionId(receipt.getTransactionId());
        return response;
    }
}

