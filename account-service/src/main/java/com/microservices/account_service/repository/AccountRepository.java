package com.microservices.account_service.repository;

import com.microservices.account_service.models.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findById(Long id);
    List<AccountEntity> findByUserId(Long userId);
}
