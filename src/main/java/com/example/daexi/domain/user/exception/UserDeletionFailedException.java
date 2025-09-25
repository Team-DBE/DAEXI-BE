package com.example.daexi.domain.user.exception;

public class UserDeletionFailedException extends RuntimeException {
    public UserDeletionFailedException(String message) {
        super(message);
    }
}
