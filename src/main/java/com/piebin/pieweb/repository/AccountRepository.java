package com.piebin.pieweb.repository;

import com.piebin.pieweb.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(String id);
}
