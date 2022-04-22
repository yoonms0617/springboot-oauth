package com.example.springbootoauth.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
