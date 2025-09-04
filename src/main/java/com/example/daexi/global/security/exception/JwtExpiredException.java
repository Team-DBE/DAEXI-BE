package com.example.daexi.global.security.exception;

public class JwtExpiredException extends RuntimeException {
    public JwtExpiredException() {
        super("token is expired");
    }
}
