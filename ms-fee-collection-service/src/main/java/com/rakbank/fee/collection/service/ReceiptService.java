package com.rakbank.fee.collection.service;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.response.ReceiptResponse;

import java.util.List;

public interface ReceiptService {
    ReceiptResponse collectFee(Receipt receipt);

    List<ReceiptResponse> getReceiptsByStudentId(String studentId);
}
