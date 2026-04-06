package com.microservices.account_service.service;

import com.microservices.account_service.models.entities.AccountEntity;
import com.microservices.account_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<AccountEntity> getAccountById(Long id) {
        Optional<AccountEntity> account = this.accountRepository.findById(id);
        return account.map(a -> ResponseEntity.ok(a)).orElseThrow(()-> new RuntimeException("no account with id: " + id));
    }

    public ResponseEntity<List<AccountEntity>> getAllAccounts() {
        List<AccountEntity> accounts =  this.accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }
}
