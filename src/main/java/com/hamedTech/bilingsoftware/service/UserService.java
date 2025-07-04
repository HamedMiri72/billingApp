package com.hamedTech.bilingsoftware.service;

import com.hamedTech.bilingsoftware.dto.UserRequest;
import com.hamedTech.bilingsoftware.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);
    String getUserRole(String email);

    List<UserResponse> readAllUsers();

    void deleteUser(String userId);

}
