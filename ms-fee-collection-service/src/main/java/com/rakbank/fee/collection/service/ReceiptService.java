package com.rakbank.fee.collection.service;

import com.rakbank.fee.collection.entity.Receipt;

import java.util.List;

public interface ReceiptService {
    Receipt collectFee(Receipt receipt);

    List<Receipt> getReceiptsByStudentId(String studentId);
}
