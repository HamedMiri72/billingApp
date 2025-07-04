package com.hamedTech.bilingsoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String userId;
    private String name;
    private String role;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
