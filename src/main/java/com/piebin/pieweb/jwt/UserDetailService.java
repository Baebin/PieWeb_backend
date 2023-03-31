package com.piebin.pieweb.jwt;

import com.piebin.pieweb.domain.Account;
import com.piebin.pieweb.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final AccountRepository repository;

    @Override
    public UserDetail loadUserByUsername(String id) throws UsernameNotFoundException {
        Account account = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + id));
        return new UserDetail(account);
    }
}
