package com.javaexercise.userregistryservice.service;

import com.javaexercise.userregistryservice.model.User;
import com.javaexercise.userregistryservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service(value = "security-user-details")
public class SecurityUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    public SecurityUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByEmail(email);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("No user found with username " + email));
        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(), optionalUser.get().getPassword(), new ArrayList<>());
    }

}
