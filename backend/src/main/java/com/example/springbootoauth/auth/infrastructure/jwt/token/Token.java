package com.example.springbootoauth.auth.infrastructure.jwt.token;

import com.example.springbootoauth.auth.infrastructure.jwt.token.TokenType;

public interface Token {

    String getSecretKey();

    Long getExprationTime();

    boolean checkTokenType(TokenType tokenType);

}
