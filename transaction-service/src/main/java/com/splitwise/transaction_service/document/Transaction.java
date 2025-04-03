package com.splitwise.transaction_service.document;

import com.splitwise.transaction_service.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    private String transactionId;
    private int friendId;
    private double amount;
    private TransactionType transactionType;
}
