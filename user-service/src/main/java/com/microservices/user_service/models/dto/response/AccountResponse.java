package com.microservices.user_service.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long id;
    private String accountNumber;
    private String type;
    private Double balance;
    private Long userId;
}
