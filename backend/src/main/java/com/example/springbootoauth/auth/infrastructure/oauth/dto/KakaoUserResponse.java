package com.example.springbootoauth.auth.infrastructure.oauth.dto;

import com.example.springbootoauth.auth.domain.SocialType;
import com.example.springbootoauth.user.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class KakaoUserResponse {

    @JsonProperty("id")
    private String socialId;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    private static class KakaoAccount {

        @JsonProperty("email")
        private String email;

    }

    public User toUser() {
        return User.builder()
                .socialId(socialId)
                .email(getKakaoAccount().getEmail())
                .socialType(SocialType.KAKAO)
                .build();
    }

}
