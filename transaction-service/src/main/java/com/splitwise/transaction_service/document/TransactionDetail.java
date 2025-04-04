package com.splitwise.transaction_service.document;

import com.splitwise.transaction_service.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "transaction-detail")
public class TransactionDetail {
    @Id
    private Long id;
    private double totalAmount;
    private TransactionType currentStatus;
    private List<Transaction> transactionList;
}
