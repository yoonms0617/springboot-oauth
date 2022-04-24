package com.example.springbootoauth.auth.exception;

import com.example.springbootoauth.common.exception.BaseException;
import com.example.springbootoauth.common.exception.ErrorCode;

import org.springframework.http.HttpStatus;

public class NotSupportedSocialType extends BaseException {

    public NotSupportedSocialType() {
        super(ErrorCode.NOT_SUPPORT_SOCIAL_TYPE, HttpStatus.UNAUTHORIZED);
    }

}
