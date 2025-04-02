package com.splitwise.auth_service.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {


    private boolean status;  // Use boolean instead of string
    private LocalDateTime timeStamp;
    private String message;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(String description, int errorCode, boolean status) {
        this();
        this.message = description;
        this.status = status;  // Assign the boolean status
    }
}
