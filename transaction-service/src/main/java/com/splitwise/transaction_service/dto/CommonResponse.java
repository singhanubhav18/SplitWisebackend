package com.splitwise.transaction_service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommonResponse<T> {
    private boolean status;
    private String description;
    private T data;
}
