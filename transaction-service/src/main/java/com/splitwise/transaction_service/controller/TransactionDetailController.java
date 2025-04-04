package com.splitwise.transaction_service.controller;

import com.splitwise.transaction_service.dto.CommonResponse;
import com.splitwise.transaction_service.dto.TransactionDto;
import com.splitwise.transaction_service.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactionDetail")
@RequiredArgsConstructor
public class TransactionDetailController {

    private final TransactionDetailService transactionService;

    @GetMapping("/getTransactionDetail")
    public ResponseEntity<CommonResponse<TransactionDto>> getTransaction(){
        return ResponseEntity.ok(transactionService.getTransactionDetail());
    }

    @GetMapping("/addTransactionDetail")
    public ResponseEntity<CommonResponse<TransactionDto>> setNewTransactionDetail(){
        return ResponseEntity.ok(transactionService.setNewTransactionDetail());
    }
}
