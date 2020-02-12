package com.javaexercise.userregistryservice.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(String username);
    Boolean validateToken(String token, UserDetails userDetails);
}
