package com.hamedTech.bilingsoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponse {


    private String email;
    private String token;
    private String role;
}
