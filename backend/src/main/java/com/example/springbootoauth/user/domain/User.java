package com.example.springbootoauth.user.domain;

import com.example.springbootoauth.auth.domain.SocialType;
import com.example.springbootoauth.common.domain.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "USER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String socialId;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Builder
    public User(String socialId, String email, SocialType socialType) {
        this.socialId = socialId;
        this.email = email;
        this.socialType = socialType;
    }

}
