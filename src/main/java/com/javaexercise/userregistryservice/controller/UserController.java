package com.javaexercise.userregistryservice.controller;

import com.javaexercise.userregistryservice.beans.UserDto;
import com.javaexercise.userregistryservice.model.User;
import com.javaexercise.userregistryservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }


}
