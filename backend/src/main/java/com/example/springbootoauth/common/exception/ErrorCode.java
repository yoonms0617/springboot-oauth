package com.example.springbootoauth.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_TOKEN("ERR-AUTH-0001", "유효하지 않는 토큰입니다."),
    NOT_SUPPORT_SOCIAL_TYPE("ERR-AUTH-002", "지원하지 않는 방식입니다."),
    LOAD_FAIL_USER_PROFILE("ERR-AUTH-003", "사용자 정보를 불러오는데 실패했습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
