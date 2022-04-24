package com.example.springbootoauth.auth.infrastructure.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class OAuthTokenResponse {

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("scope")
    private String scope;

}
