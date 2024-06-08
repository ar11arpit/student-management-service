package com.rakbank.fee.collection.service.impl;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.dto.Student;
import com.rakbank.fee.collection.exceptions.CustomException;
import com.rakbank.fee.collection.exceptions.ErrorType;
import com.rakbank.fee.collection.repository.ReceiptRepository;
import com.rakbank.fee.collection.dto.ReceiptResponse;
import com.rakbank.fee.collection.service.ReceiptService;
import com.rakbank.fee.collection.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final RestTemplate restTemplate;

    @Value("${api.student.details}")
    private String studentDetailsEndpoint;

    public ReceiptResponse collectFee(Receipt receipt) throws CustomException {
        ResponseEntity<Student> response = restTemplate.getForEntity(
                studentDetailsEndpoint + receipt.getStudentId(), Student.class);
        Student student = response.getBody();
        if (response.getBody()==null) {
            throw new CustomException("Student not found", ErrorType.FUNCTIONAL);
        }
        if(student.getYearlyFee().compareTo(receipt.getAmount())==0){
            throw new CustomException("Fees is paid fully.", ErrorType.FUNCTIONAL);
        }
        receipt.setTransactionId(Utils.generateTransactionId());
        receipt.setReferenceNumber(Utils.generateReferenceId());
        ReceiptResponse receiptResponse = mapToResponse(receiptRepository.save(receipt));
        receiptResponse.setGrade(student.getGrade());

        return receiptResponse;
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

