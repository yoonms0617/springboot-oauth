package com.example.springbootoauth.auth.infrastructure.oauth;

import com.example.springbootoauth.auth.domain.SocialType;
import com.example.springbootoauth.user.domain.User;

public interface OAuthClient {

    User getUserInfo(String authorizationCode);

    boolean isSupportSocialType(SocialType socialType);

}
