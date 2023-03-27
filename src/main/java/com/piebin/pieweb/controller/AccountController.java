package com.piebin.pieweb.controller;

import com.google.gson.JsonObject;
import com.piebin.pieweb.domain.Account;
import com.piebin.pieweb.dto.AccountRequestDto;
import com.piebin.pieweb.jwt.TokenProvider;
import com.piebin.pieweb.repository.AccountRepository;
import com.piebin.pieweb.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AccountRepository accountRepository;

    @PostMapping("/api/signup")
    public ResponseEntity register(@RequestBody AccountRequestDto dto) {
        Optional<Account> account = accountRepository.findById(dto.getId());

        // 중복된 사용자
        if (account.isPresent())
            throw new IllegalArgumentException("사용할 수 없는 아이디입니다.");
            //return ResponseEntity.badRequest().build();

        // 패스워드 불일치
        if (!dto.getPassword().equals(dto.getPasswordConfirm()))
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
            //return ResponseEntity.badRequest().build();

        accountService.register(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/signin")
    public String login(@RequestBody Map<String, String> map) {
        Account account = accountRepository.findById(map.get("id"))
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 계정입니다."));

        if (!passwordEncoder.matches(map.get("password"), account.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("token", tokenProvider.create(account.getId(), account.getRoles()));
        return jsonObject.toString();
    }
}
