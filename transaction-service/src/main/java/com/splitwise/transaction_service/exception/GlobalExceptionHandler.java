package com.splitwise.transaction_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoTransactionFound.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(NoTransactionFound exception) {
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), false);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
