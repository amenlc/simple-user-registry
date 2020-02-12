package com.javaexercise.userregistryservice.service;

import com.javaexercise.userregistryservice.beans.UserDto;
import com.javaexercise.userregistryservice.model.User;
import com.javaexercise.userregistryservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = mapDtoToUser(userDto);
        userRepository.findByEmail(user.getEmail())
                .ifPresent(t -> {
                    throw new ConstraintViolationException("Usuario con el correo: " + t.getEmail() + ", se encuentra registrado",
                            Collections.emptySet());
                });
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setActive(true);
        user.setToken(jwtService.generateToken(user.getEmail()));
        return mapUserToDto(userRepository.save(user));
    }

    private UserDto mapUserToDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(),
                user.getPassword(), user.getPhones(), user.getCreated(), user.getModified(),
                user.getLastLogin(), user.getToken(), user.isActive());
    }

    private User mapDtoToUser(UserDto user) {
        return new User(null, user.getName(), user.getEmail(), user.getPassword(),
                user.getPhones(), null, null, null, null, false);
    }

}