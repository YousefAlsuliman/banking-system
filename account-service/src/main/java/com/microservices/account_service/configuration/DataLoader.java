package com.microservices.account_service.configuration;

import com.microservices.account_service.models.entities.AccountEntity;
import com.microservices.account_service.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(AccountRepository accountRepository){
        return args -> {

            accountRepository.save(new AccountEntity(
                    null, "ACC1001", "SAVINGS", 2500.0, 1L
            ));
            accountRepository.save(new AccountEntity(
                    null, "ACC1002", "CURRENT", 5000.0, 1L
            ));
            accountRepository.save(new AccountEntity(
                    null, "ACC2001", "SAVINGS", 1200.0, 2L
            ));
            accountRepository.save(new AccountEntity(
                    null, "ACC3001", "CURRENT", 8000.0, 3L
            ));
            accountRepository.save(new AccountEntity(
                    null, "ACC4001", "SAVINGS", 150.0, 4L
            ));
            System.out.println("✅ Dummy accounts inserted!");
        };
    }
}
