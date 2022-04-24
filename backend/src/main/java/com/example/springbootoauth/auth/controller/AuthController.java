package com.example.springbootoauth.auth.controller;

import com.example.springbootoauth.auth.dto.TokenResponse;
import com.example.springbootoauth.auth.infrastructure.jwt.token.TokenType;
import com.example.springbootoauth.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private static final String REFRESH_TOKEN_NAME = "bt";

    private final AuthService authService;

    @GetMapping("/oauth/{provider}/login")
    public ResponseEntity<TokenResponse> login(@PathVariable("provider") String provider,
                                               @RequestParam("code") String authorizationCode,
                                               HttpServletResponse response) {
        TokenResponse accessToken = authService.oAuthSignIn(provider, authorizationCode);
        TokenResponse refreshToken = authService.createRefreshToken(accessToken.getToken(), TokenType.ACCESS);
        String cookie = createCookie(refreshToken);
        response.addHeader("Set-Cookie", cookie);
        return ResponseEntity.ok(accessToken);
    }

    private String createCookie(TokenResponse refreshToken) {
        return ResponseCookie.from(REFRESH_TOKEN_NAME, refreshToken.getToken())
                .sameSite("Lax")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .maxAge(refreshToken.getExpire())
                .domain("localhost")
                .build().toString();
    }

}
