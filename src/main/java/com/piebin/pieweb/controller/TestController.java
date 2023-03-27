package com.piebin.pieweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TestController {
    @GetMapping("/hello")
    public List<String> getGello() {
        return Arrays.asList("안녕", "Hi!");
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "안녕하세요, 현재 서버 시간은 " + new Date() + "입니다. \n";
    }

    @GetMapping("/api/tester")
    public Map<String, Object> tester() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", hello());
        return map;
    }

    @PostMapping("/api/testpost")
    public Map<String, Object> TestPost() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", hello());
        return map;
    }
}