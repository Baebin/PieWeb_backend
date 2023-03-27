package com.piebin.pieweb.dto;

import lombok.Getter;

@Getter
public class AccountRequestDto {
    private String id;
    private String password;
    private String passwordConfirm;
}