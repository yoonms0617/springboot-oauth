package com.example.springbootoauth.auth.service;

import com.example.springbootoauth.auth.infrastructure.jwt.token.TokenType;
import com.example.springbootoauth.auth.infrastructure.oauth.OAuthClient;
import com.example.springbootoauth.auth.domain.SocialType;
import com.example.springbootoauth.auth.dto.TokenResponse;
import com.example.springbootoauth.auth.infrastructure.jwt.JwtProvider;
import com.example.springbootoauth.auth.infrastructure.oauth.OAuthClientFactory;
import com.example.springbootoauth.user.domain.User;
import com.example.springbootoauth.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final OAuthClientFactory oAuthClientFactory;

    @Transactional
    public TokenResponse oAuthSignIn(String provider, String authorizationCode) {
        SocialType socialType = SocialType.of(provider);
        OAuthClient oAuthClient = oAuthClientFactory.getOAuthClientBySocialType(socialType);
        User userInfo = oAuthClient.getUserInfo(authorizationCode);
        User user = userRepository.findBySocialIdAndSocialType(userInfo.getSocialId(), userInfo.getSocialType())
                .orElseGet(() -> userRepository.save(userInfo));
        return jwtProvider.createAccessToken(user.getId());
    }

    public TokenResponse createRefreshToken(String token, TokenType tokenType) {
        Long id = jwtProvider.getIdFromPayload(token, tokenType);
        return jwtProvider.createRefreshToken(id);
    }

}
