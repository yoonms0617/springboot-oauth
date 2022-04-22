package com.example.springbootoauth.user.repository;

import com.example.springbootoauth.auth.domain.SocialType;
import com.example.springbootoauth.user.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialIdAndSocialType(String socialId, SocialType socialType);

}
