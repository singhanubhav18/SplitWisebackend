package com.splitwise.auth_service.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {


    private boolean status;  // Use boolean instead of string
    private Response response;

    public ApiError() {
        this.status = false;
        this.response=new Response();
    }

    public ApiError(String message, boolean status) {
        this();
        this.status = status;
        this.response.setMessage(message);
        this.response.setTimestamp(LocalDateTime.now());
    }
    @Data
    public static class Response {
        private LocalDateTime timestamp;
        private String message;
    }
}
