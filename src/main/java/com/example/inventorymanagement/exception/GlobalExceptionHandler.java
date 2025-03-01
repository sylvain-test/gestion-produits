package com.example.inventorymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;




import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
        return new ErrorResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicate(DuplicateException ex) {
        return new ErrorResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.CONFLICT.value());
    }

    // Méthode pour gérer les erreurs de validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        // Récupère le premier message d'erreur
        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ErrorResponse(LocalDateTime.now(), msg, HttpStatus.BAD_REQUEST.value());
    }

    // Handler générique si vous le souhaitez
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneric(Exception ex) {
        return new ErrorResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    // GlobalExceptionHandler.java
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentials(BadCredentialsException ex) {
        return new ErrorResponse(
                LocalDateTime.now(),
                "Bad credentials",
                HttpStatus.UNAUTHORIZED.value()
        );
    }
}