package com.microservices.account_service.controller;

import com.microservices.account_service.models.entities.AccountEntity;
import com.microservices.account_service.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    private ResponseEntity<AccountEntity> getAccountById(@PathVariable Long id){
        return ResponseEntity.ok(this.accountService.getAccountById(id));
    }

    @GetMapping
    private ResponseEntity<List<AccountEntity>> getAllAccounts(){
        return ResponseEntity.ok(this.accountService.getAllAccounts());
    }

    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(@Valid @RequestBody AccountEntity account) {
        return ResponseEntity.ok(this.accountService.createAccount(account));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountEntity>> getAccountsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountEntity> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountEntity account) {
        return ResponseEntity.ok(this.accountService.updateAccount(id, account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.ok(this.accountService.deleteAccount(id));
    }
}
