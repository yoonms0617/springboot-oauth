package com.example.springbootoauth.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TokenResponse {

    private String token;
    private Long expire;

    public TokenResponse(String token, Long expire) {
        this.token = token;
        this.expire = expire;
    }

}
