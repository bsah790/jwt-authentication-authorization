package com.secure.jwtimplementation.service;

import com.secure.jwtimplementation.model.UserRequest;
import com.secure.jwtimplementation.model.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest);
    UserResponse getUser();
    List<UserResponse> getAllUser();
}
