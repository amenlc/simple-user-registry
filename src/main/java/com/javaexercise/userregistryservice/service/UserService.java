package com.javaexercise.userregistryservice.service;

import com.javaexercise.userregistryservice.beans.UserDto;

public interface UserService {
    UserDto registerUser(UserDto user);
}
