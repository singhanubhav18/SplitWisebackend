package com.splitwise.transaction_service.controller;
import com.splitwise.transaction_service.dto.CommonResponse;
import com.splitwise.transaction_service.dto.TransactionDto;
import com.splitwise.transaction_service.dto.UserDetailsDto;
import com.splitwise.transaction_service.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactionDetail")
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/searchUser/{name}")
    public ResponseEntity<CommonResponse<List<UserDetailsDto>>> searchUserByName(@PathVariable String name) {
        CommonResponse<List<UserDetailsDto>> response = transactionService.searchUserByName(name);
        return ResponseEntity.ok(response);
    }
}
