package com.example.roster.web;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.roster.persistence.ValidationException;

/*
 * Handles all the exceptions raised by the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /*
     * Send 400 http code for application validation errors
     */
    @ExceptionHandler(value = { ValidationException.class })
    public ResponseEntity<String> handleValidationException(ValidationException ex, HttpServletRequest request) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /*
     * Send 400 http code for validation errors thrown by spring
     */
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<String> handleInvalidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errors = ex.getBindingResult().getFieldErrors()
                .stream().map(f -> f.getDefaultMessage()).collect(Collectors.joining("\n"));

        return new ResponseEntity<String>(errors, HttpStatus.BAD_REQUEST);
    }

    /*
     * Catch all other exceptions and send 500 error code
     */
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<String> handleException(Exception ex, HttpServletRequest request) {
        //TODO: Log the exception

        return new ResponseEntity<String>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}