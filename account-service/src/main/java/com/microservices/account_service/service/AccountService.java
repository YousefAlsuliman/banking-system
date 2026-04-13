package com.microservices.account_service.service;

import com.microservices.account_service.models.entities.AccountEntity;
import com.microservices.account_service.repository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity getAccountById(Long id) {
        Optional<AccountEntity> account = this.accountRepository.findById(id);
        return account.orElseThrow(() -> new RuntimeException("no account with id: " + id));
    }

    public List<AccountEntity> getAllAccounts() {
        List<AccountEntity> accounts = this.accountRepository.findAll();
        return accounts;
    }

    public AccountEntity createAccount(@Valid AccountEntity account) {
        this.accountRepository.save(account);
        return account;
    }

    public List<AccountEntity> getAccountsByUserId(Long userId) {
        List<AccountEntity> accounts = this.accountRepository.findByUserId(userId);
        if (accounts.isEmpty()) {
            throw new RuntimeException("no accounts with userId: " + userId);
        }
        return accounts;
    }

    public AccountEntity updateAccount(Long id, AccountEntity account) {
        Optional<AccountEntity> targetAccount = this.accountRepository.findById(id);

        AccountEntity updatedAccount = targetAccount.map(a ->{
            a.setAccountNumber(account.getAccountNumber());
            a.setBalance(account.getBalance());
            a.setType(account.getType());
            a.setUserId(account.getUserId());
            this.accountRepository.save(a);
            return a;
        }).orElseThrow(() -> new RuntimeException("no account with id: " + id));

        return updatedAccount;
    }

    public String deleteAccount(Long id) {
        if(this.accountRepository.existsById(id)){
            this.accountRepository.deleteById(id);
            return "Account deleted successfully";
        }
        throw new RuntimeException("no account with id: " + id);
    }
}
