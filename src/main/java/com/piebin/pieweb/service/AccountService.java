package com.piebin.pieweb.service;

import com.piebin.pieweb.domain.Account;
import com.piebin.pieweb.dto.AccountRequestDto;
import com.piebin.pieweb.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    @Autowired
    PasswordEncoder encoder;

    public Account register(AccountRequestDto dto) {
        Account account = new Account(
            dto.getId(),
            encoder.encode(dto.getPassword())
        );
        account.setSignup_date(LocalDateTime.now());
        account.setRoles(Collections.singletonList("ROLE_USER"));

        return repository.save(
                account
        );
    }
}
