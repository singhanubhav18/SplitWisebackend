package com.splitwise.transaction_service.exception;

public class NoTransactionFound  extends RuntimeException{
    public NoTransactionFound(String message) {
        super(message);
    }
}