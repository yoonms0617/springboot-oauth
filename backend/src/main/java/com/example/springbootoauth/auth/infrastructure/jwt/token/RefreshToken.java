package com.example.springbootoauth.auth.infrastructure.jwt.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RefreshToken implements Token {

    @Value("${jwt.refresh-token.secret-key}")
    private String secretKey;

    @Value("${jwt.refresh-token.expiration-time}")
    private Long expirationTime;

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public Long getExprationTime() {
        return expirationTime;
    }

    @Override
    public boolean checkTokenType(TokenType tokenType) {
        return TokenType.REFRESH == tokenType;
    }

}
