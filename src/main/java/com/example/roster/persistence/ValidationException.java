package com.example.roster.persistence;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg) {
        super(msg);
    }
}