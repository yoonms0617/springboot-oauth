package com.example.springbootoauth.auth.exception;

import com.example.springbootoauth.common.exception.BaseException;
import com.example.springbootoauth.common.exception.ErrorCode;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BaseException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
    }

}
