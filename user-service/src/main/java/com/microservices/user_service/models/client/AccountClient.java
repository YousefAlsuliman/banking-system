package com.microservices.user_service.models.client;

import com.microservices.user_service.models.dto.response.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "account-service", url = "http://localhost:8082")
// name and url are used for (service-discovery): eureka server stores ports of each microservice
public interface AccountClient {

    @GetMapping("/v1/accounts/user/{userId}") // the api : http://localhost:8082/v1/accounts/user/{userId}
    ResponseEntity<List<AccountResponse>> getAccountsByUserId(@PathVariable Long userId); // the method to call the api

}
