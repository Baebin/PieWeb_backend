package com.piebin.pieweb.controller;

import com.piebin.pieweb.domain.Account;
import com.piebin.pieweb.dto.AccountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {
    @PostMapping
    @Transactional(readOnly = false)
    public ResponseEntity register(@RequestBody AccountRequestDto dto) {
        return null;
    }
}
