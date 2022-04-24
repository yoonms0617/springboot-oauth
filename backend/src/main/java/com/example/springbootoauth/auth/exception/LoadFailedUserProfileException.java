package com.example.springbootoauth.auth.exception;

import com.example.springbootoauth.common.exception.BaseException;
import com.example.springbootoauth.common.exception.ErrorCode;

import org.springframework.http.HttpStatus;

public class LoadFailedUserProfileException extends BaseException {

    public LoadFailedUserProfileException() {
        super(ErrorCode.LOAD_FAIL_USER_PROFILE, HttpStatus.UNAUTHORIZED);
    }

}
