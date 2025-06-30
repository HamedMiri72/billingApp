package com.hamedTech.bilingsoftware.controller;

import com.hamedTech.bilingsoftware.dto.AuthRequest;
import com.hamedTech.bilingsoftware.dto.AuthResponse;
import com.hamedTech.bilingsoftware.service.impl.AppUserDaetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AppUserDaetailsService appUserDaetailsService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){

        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails = appUserDaetailsService.loadUserByUsername(request.getEmail());

        return null;
    }

    private void authenticate(String email, String password) {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch(DisabledException e){

            throw new DisabledException("User is disabled");

        }catch(BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
    }
    @PostMapping("/encode")
    public String encodePassword(@RequestBody Map<String, String> request){

        return passwordEncoder.encode(request.get("password"));

    }
}
