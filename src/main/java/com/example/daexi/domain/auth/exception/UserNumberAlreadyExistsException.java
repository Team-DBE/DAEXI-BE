package com.example.daexi.domain.auth.exception;

public class UserNumberAlreadyExistsException extends RuntimeException {
    public UserNumberAlreadyExistsException() {
        super("User number already exists");
    }
}
