package com.splitwise.transaction_service.entity;

import com.splitwise.transaction_service.enums.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDetail {
    @Id
    private String expenseId;

    @NotEmpty(message = "Expense title cannot be empty")
    private String expenseTitle;

    private Double totalAmount;
    @NotNull(message = "Transaction type must be specified")
    private TransactionType transactionType;
}
