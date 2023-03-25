package com.piebin.pieweb.service;

import com.piebin.pieweb.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private AccountRepository repository;
}
