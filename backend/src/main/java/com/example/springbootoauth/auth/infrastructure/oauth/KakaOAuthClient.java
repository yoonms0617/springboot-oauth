package com.example.springbootoauth.auth.infrastructure.oauth;

import com.example.springbootoauth.auth.domain.SocialType;
import com.example.springbootoauth.auth.exception.LoadFailedUserProfileException;
import com.example.springbootoauth.auth.infrastructure.oauth.dto.KakaoUserResponse;
import com.example.springbootoauth.auth.infrastructure.oauth.dto.OAuthTokenResponse;
import com.example.springbootoauth.user.domain.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaOAuthClient implements OAuthClient {

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${oauth.kakao.client-secret}")
    private String clientSecret;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${oauth.kakao.token-uri}")
    private String tokenUri;

    @Value("${oauth.kakao.user-info-uri}")
    private String userInfoUri;

    @Override
    public User getUserInfo(String authorizationCode) {
        OAuthTokenResponse oAuthTokenResponse = getOAuthTokenResponse(authorizationCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", oAuthTokenResponse.getTokenType() + " " + oAuthTokenResponse.getAccessToken());
        headers.add("Content-type", "application/x-www-from-urlencoded;charset=utf-8");
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        KakaoUserResponse kakaoUserResponse = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, KakaoUserResponse.class).getBody();
        validateNotNull(kakaoUserResponse);
        return kakaoUserResponse.toUser();
    }

    private OAuthTokenResponse getOAuthTokenResponse(String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", authorizationCode);
        params.add("client_secret", clientSecret);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(tokenUri, HttpMethod.POST, request, OAuthTokenResponse.class).getBody();
    }

    @Override
    public boolean isSupportSocialType(SocialType socialType) {
        return SocialType.KAKAO == socialType;
    }

    private void validateNotNull(KakaoUserResponse kakaoUserResponse) {
        if (kakaoUserResponse == null) {
            throw new LoadFailedUserProfileException();
        }
    }

}
