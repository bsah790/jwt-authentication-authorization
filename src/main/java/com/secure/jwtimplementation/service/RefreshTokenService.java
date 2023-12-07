package com.secure.jwtimplementation.service;

import com.secure.jwtimplementation.entity.RefreshToken;
import com.secure.jwtimplementation.entity.UserInfo;
import com.secure.jwtimplementation.repository.RefreshTokenRepository;
import com.secure.jwtimplementation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    public RefreshToken createRefreshToken(String username){
        UserInfo userInfo = userRepository.findByUsername(username);
        Optional<RefreshToken> token = refreshTokenRepository.findByUserInfo(userInfo);
        RefreshToken refreshToken = null;
        if(token.isPresent()) {
            refreshToken = RefreshToken.builder()
                    .userInfo(userInfo)
                    .token(token.get().getToken())
                    .expiryDate(Instant.now().plusMillis(600000))
                    .id(token.get().getId())
                    .build();
        } else {
            refreshToken = RefreshToken.builder()
                    .userInfo(userInfo)
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(600000))
                    .build();
        }
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }
}
