package com.piebin.pieweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class PiewebApplication {
    public static void main(String[] args) {
        SpringApplication.run(PiewebApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World Spring!";
    }
}
