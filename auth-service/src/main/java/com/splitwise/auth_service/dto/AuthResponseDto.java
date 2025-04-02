package com.splitwise.auth_service.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private Boolean status;
    private String message;
    private Response response;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String authToken;
    }
}
