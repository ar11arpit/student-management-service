package com.rakbank.fee.collection.controller;

import com.rakbank.fee.collection.entity.Receipt;
import com.rakbank.fee.collection.service.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipt")
@Tag(name = "Receipt API", description = "API for managing fee receipts")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @PostMapping
    @Operation(summary = "Collect fee", description = "Collect fee and generate a receipt")
    public ResponseEntity<Receipt> collectFee(@Valid @RequestBody Receipt receipt) {
        Receipt savedReceipt = receiptService.collectFee(receipt);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReceipt);
    }

    @GetMapping("/{studentId}")
    @Operation(summary = "Get receipts by student ID", description = "Retrieve all receipts for a specific student by their ID")
    public ResponseEntity<List<Receipt>> getReceipts(@PathVariable String studentId) {
        List<Receipt> receipts = receiptService.getReceiptsByStudentId(studentId);
        return ResponseEntity.ok(receipts);
    }
}

