package com.example.springbootoauth.auth.domain;

import com.example.springbootoauth.auth.exception.NotSupportedSocialType;

import java.util.Arrays;

public enum SocialType {

    KAKAO, GOOGLE, NAVER;

    public static SocialType of(String provider) {
        return Arrays.stream(SocialType.values())
                .filter(socialType -> socialType.name().equalsIgnoreCase(provider))
                .findFirst()
                .orElseThrow(NotSupportedSocialType::new);
    }

}
