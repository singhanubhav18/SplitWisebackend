package com.splitwise.auth_service.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String status;
    private Response response;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String authToken;
    }
}
