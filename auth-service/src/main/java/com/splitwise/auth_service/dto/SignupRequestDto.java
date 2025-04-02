package com.splitwise.auth_service.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    private String imageUrl;
}
