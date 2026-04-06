package com.microservices.account_service.controller;

import com.microservices.account_service.models.entities.AccountEntity;
import com.microservices.account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    private ResponseEntity<AccountEntity> getAccountById(@PathVariable Long id){
        return this.accountService.getAccountById(id);
    }

    @GetMapping
    private ResponseEntity<List<AccountEntity>> getAllAccounts(){
        return this.accountService.getAllAccounts();
    }
}
