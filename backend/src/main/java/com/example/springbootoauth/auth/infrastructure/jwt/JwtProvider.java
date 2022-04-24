package com.example.springbootoauth.auth.infrastructure.jwt;

import com.example.springbootoauth.auth.dto.TokenResponse;
import com.example.springbootoauth.auth.exception.InvalidTokenException;
import com.example.springbootoauth.auth.infrastructure.jwt.token.Token;
import com.example.springbootoauth.auth.infrastructure.jwt.token.TokenType;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final Token accessToken;
    private final Token refreshToken;

    public TokenResponse createAccessToken(Long id) {
         return createToken(id, accessToken.getSecretKey(), accessToken.getExprationTime());
    }

    public TokenResponse createRefreshToken(Long id) {
         return createToken(id, refreshToken.getSecretKey(), refreshToken.getExprationTime());
    }

    private TokenResponse createToken(Long id, String secretKey, Long expirationTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);
        String token = Jwts.builder()
                .claim("id", id)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return new TokenResponse(token, expiration.getTime());
    }

    public Long getIdFromPayload(String token, TokenType tokenType) {
        String secretKey = getSecretkeyFromTokenType(tokenType);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().get("id", Long.class);
    }

    private String getSecretkeyFromTokenType(TokenType tokenType) {
        if (accessToken.checkTokenType(tokenType)) {
            return accessToken.getSecretKey();
        }
        return refreshToken.getSecretKey();
    }

    public void validateToken(String token, TokenType tokenType) {
        try {
            String secretKey = getSecretkeyFromTokenType(tokenType);
            Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        }
    }

}
