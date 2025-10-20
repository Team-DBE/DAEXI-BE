package com.example.daexi.domain.auth.exception;

import com.example.daexi.global.exception.ErrorCode;
import com.example.daexi.global.exception.GlobalException;

public class InvalidPasswordException extends GlobalException {
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
