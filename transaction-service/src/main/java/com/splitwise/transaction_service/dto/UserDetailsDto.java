package com.splitwise.transaction_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
