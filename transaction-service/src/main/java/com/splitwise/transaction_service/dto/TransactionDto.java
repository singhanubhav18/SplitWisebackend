package com.splitwise.transaction_service.dto;

import com.splitwise.transaction_service.document.Transaction;
import com.splitwise.transaction_service.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {
    private Long id;
    private double totalAmount;
    private TransactionType currentStatus;
    private List<Transaction> transactionList;

}
