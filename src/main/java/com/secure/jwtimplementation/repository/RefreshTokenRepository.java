package com.secure.jwtimplementation.repository;

import com.secure.jwtimplementation.entity.RefreshToken;
import com.secure.jwtimplementation.entity.UserInfo;
import com.secure.jwtimplementation.helper.RefreshableCRUDRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends RefreshableCRUDRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserInfo(UserInfo userInfo);
}
