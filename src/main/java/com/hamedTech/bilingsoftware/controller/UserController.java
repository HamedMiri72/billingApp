package com.hamedTech.bilingsoftware.controller;


import com.hamedTech.bilingsoftware.dto.UserRequest;
import com.hamedTech.bilingsoftware.dto.UserResponse;
import com.hamedTech.bilingsoftware.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {


    private final UserService userService;


    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRequest userRequest){

        try{
            UserResponse userResponse = userService.createUser(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse).getBody();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unable to register User" + e.getMessage());

        }

    }


    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAll(){
        try{

            List<UserResponse> responses =  userService.readAllUsers();

            return ResponseEntity.ok().body(responses);

        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to catch the ussers" + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){

       try{
           userService.deleteUser(userId);
           return ResponseEntity.status(HttpStatus.OK).build();
       }catch(Exception e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found" + e.getMessage());
       }
    }
}
