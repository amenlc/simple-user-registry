package com.javaexercise.userregistryservice.exception;

import com.google.gson.Gson;
import com.javaexercise.userregistryservice.beans.ErrorMessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UsernameNotFoundException.class, BadCredentialsException.class})
    protected ResponseEntity<Object> handleUsernameNotFound(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse =
                (new Gson()).toJson(new ErrorMessageResponse("Usuario o clave invalidos"));
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleUserAlreadyExists(
            ConstraintViolationException ex, WebRequest request) {

        List<ErrorMessageResponse> errorMessages = new ArrayList<>();
        if(ex.getConstraintViolations().isEmpty()) {
            errorMessages.add(new ErrorMessageResponse("Usuario registrado previamente"));
        } else {
            for (final ConstraintViolation<?> cv : ex.getConstraintViolations()){
                errorMessages.add(new ErrorMessageResponse(cv.getMessage()));
            }
        }
        String bodyOfResponse = (new Gson()).toJson(errorMessages);

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
