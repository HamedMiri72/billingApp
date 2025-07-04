package com.hamedTech.bilingsoftware.service.impl;

import com.hamedTech.bilingsoftware.dto.UserRequest;
import com.hamedTech.bilingsoftware.dto.UserResponse;
import com.hamedTech.bilingsoftware.entity.UserEntity;
import com.hamedTech.bilingsoftware.mapper.UserMapper;
import com.hamedTech.bilingsoftware.repository.UserRepository;
import com.hamedTech.bilingsoftware.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {

        UserEntity newUser = userRepository.save(userMapper.convertToEntity(request));
        UserResponse response = userMapper.convertToUserResponse(newUser);

        return response;

    }

    @Override
    public String getUserRole(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by provided email: " + email));
        String role = userEntity.getRole();

        return role;
    }

    @Override
    public List<UserResponse> readAllUsers() {

        List<UserEntity> userEntityList = userRepository.findAll();

        List<UserResponse> responses = userEntityList
                .stream()
                .map(userEntity -> UserMapper.convertToUserResponse(userEntity))
                .collect(Collectors.toList());

        return responses;
    }

    @Override
    public void deleteUser(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("user not found by the email: " + userId));

        userRepository.delete(userEntity);
        

    }
}
