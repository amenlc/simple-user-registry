package com.javaexercise.userregistryservice.controller;

import com.google.gson.Gson;
import com.javaexercise.userregistryservice.beans.ErrorMessageResponse;
import com.javaexercise.userregistryservice.model.AuthRequest;
import com.javaexercise.userregistryservice.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping(
            value = "/authenticate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            return ResponseEntity.ok(jwtService.generateToken(authRequest.getEmail()));
        } catch (UsernameNotFoundException ex) {
            String response = (new Gson()).toJson(new ErrorMessageResponse("Usuario no encontrado"));
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, response, ex);
        } catch (Exception ex) {
            String response = (new Gson()).toJson(new ErrorMessageResponse("Error interno de la aplicacion"));
            log.error(ex.getMessage(),ex);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, response, ex);
        }
    }
}
