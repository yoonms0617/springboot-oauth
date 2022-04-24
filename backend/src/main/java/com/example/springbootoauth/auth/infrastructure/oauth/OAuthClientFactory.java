package com.example.springbootoauth.auth.infrastructure.oauth;

import com.example.springbootoauth.auth.domain.SocialType;
import com.example.springbootoauth.auth.exception.NotSupportedSocialType;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthClientFactory {

    private final List<OAuthClient> oAuthClients;

    public OAuthClient getOAuthClientBySocialType(SocialType socialType) {
        return oAuthClients.stream()
                .filter(oAuthClient -> oAuthClient.isSupportSocialType(socialType))
                .findFirst()
                .orElseThrow(NotSupportedSocialType::new);
    }

}
