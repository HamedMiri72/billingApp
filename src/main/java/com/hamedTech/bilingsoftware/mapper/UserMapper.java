package com.hamedTech.bilingsoftware.mapper;

import com.hamedTech.bilingsoftware.dto.UserRequest;
import com.hamedTech.bilingsoftware.dto.UserResponse;
import com.hamedTech.bilingsoftware.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
    public static UserEntity convertToEntity(UserRequest request) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setName(request.getName());
        userEntity.setPassword(request.getPassword());
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setRole(request.getRole().toUpperCase());

        return userEntity;
    }

    public static UserResponse convertToUserResponse(UserEntity userEntity) {

        UserResponse userResponse = new UserResponse();

        userResponse.setEmail(userEntity.getEmail());
        userResponse.setName(userEntity.getName());
        userResponse.setUserId(userEntity.getUserId());
        userResponse.setCreatedAt(userEntity.getCreatedAt());
        userResponse.setUpdatedAt(userEntity.getUpdatedAt());
        userResponse.setRole(userEntity.getRole());

        return userResponse;

    }
}
