package com.piebin.pieweb.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.RequiredArgsConstructor;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    // Reference: https://github.com/seongbinko/hanghae99_books

    private String secretKey = "This is Piebin's Secret Code (Birthday: 20040505)";
    private final long VALIDATION_TIME = 1000 * 60 * 30;
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        // Base64 암호화
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String create(String pk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(pk);
        claims.put("roles", roles);

        Date cur = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(cur)
                .setExpiration(new Date(cur.getTime() + VALIDATION_TIME))
                // 암호화 방식
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // 토큰 생성
                .compact();
    }

    // 인증 성공시 SecurityContextHolder에 저장할 Authentication 객체 생성
    public Authentication getAuthentication(String token) {
        UserDetails details = userDetailsService.loadUserByUsername(getPK(token));
        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    // 회원 정보 추출
    public String getPK(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolve(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 & 만료 일자 확인
    public boolean validate(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
