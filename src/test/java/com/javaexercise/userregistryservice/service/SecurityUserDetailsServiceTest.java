package com.javaexercise.userregistryservice.service;

import com.javaexercise.userregistryservice.model.User;
import com.javaexercise.userregistryservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SecurityUserDetailsServiceTest {

    @Mock
    UserRepository userRepositoryMock;

    @InjectMocks
    SecurityUserDetailsService securityUserDetailsService;

//    @BeforeEach
//    void setUp() {
//
//    }

    @Test
    void when_noUserRegistered_loadUserByUsername() {
        // Optional<User> oUser = Optional.of(new User())
        when(userRepositoryMock.findByEmail("nouser@mail.com")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,
                () -> securityUserDetailsService.loadUserByUsername("nouser@mail.com"));
    }

    @Test
    void when_userRegistered_loadUserByUsername() {
         Optional<User> oUser = Optional.of(new User(
                 1l,"Richard Tapias", "registeradmin@gmail.com", "admin123", Collections.emptyList()
         ));
        when(userRepositoryMock.findByEmail("registeradmin@gmail.com")).thenReturn(oUser);
        assertNotNull(securityUserDetailsService.loadUserByUsername("registeradmin@gmail.com"));
    }
}