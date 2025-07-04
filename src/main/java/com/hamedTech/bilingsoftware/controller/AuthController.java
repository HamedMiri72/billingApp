package com.hamedTech.bilingsoftware.controller;

import com.hamedTech.bilingsoftware.dto.AuthRequest;
import com.hamedTech.bilingsoftware.dto.AuthResponse;
import com.hamedTech.bilingsoftware.service.UserService;
import com.hamedTech.bilingsoftware.service.impl.AppUserDaetailsService;
import com.hamedTech.bilingsoftware.utils.JwtUtils;
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
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){

        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails = appUserDaetailsService.loadUserByUsername(request.getPassword());
        final String token = jwtUtils.generateToken(userDetails);
        final String role = userService.getUserRole(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(
                request.getEmail(),
                token,
                role
        ));
    }

    private void authenticate(String email, String password) {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch(DisabledException e){
            throw  new DisabledException("user is disabled");
        }catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "invalid credentials");
        }
    }

    @PostMapping("/encode")
    public String encodePassword(@RequestBody Map<String, String> request){

        return passwordEncoder.encode(request.get("password"));

    }
}
