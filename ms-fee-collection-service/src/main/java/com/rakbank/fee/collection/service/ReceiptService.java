package com.rakbank.fee.collection.service;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.dto.ReceiptResponse;
import com.rakbank.fee.collection.exceptions.CustomException;

import java.util.List;

public interface ReceiptService {
    ReceiptResponse collectFee(Receipt receipt) throws CustomException;

    List<ReceiptResponse> getReceiptsByStudentId(String studentId);
}
