package com.microservices.user_service.models.client;

import com.microservices.user_service.models.dto.response.AccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collections;
import java.util.List;

@Component
public class AccountClientFallback implements AccountClient{

    @Override
    public ResponseEntity<List<AccountResponse>> getAccountsByUserId(@PathVariable Long userId){
        // Called automatically when account-service is down or circuit is OPEN
        System.out.println("FALLBACK: account-service unavailable for userId=" + userId);
        return ResponseEntity.ok(Collections.emptyList());
    }
}
