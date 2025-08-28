package com.example.daexi.global.security.exception;

public class JwtInvalidException extends RuntimeException {
    public JwtInvalidException() {
        super("token is invalid");
    }
}
