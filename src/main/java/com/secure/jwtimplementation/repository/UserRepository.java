package com.secure.jwtimplementation.repository;

import com.secure.jwtimplementation.entity.UserInfo;
import com.secure.jwtimplementation.helper.RefreshableCRUDRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RefreshableCRUDRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
    UserInfo findFirstById(Long id);
}
