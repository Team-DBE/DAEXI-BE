package com.example.daexi.domain.auth.exception;

import com.example.daexi.global.exception.ErrorCode;
import com.example.daexi.global.exception.GlobalException;

public class UserNumberAlreadyExistsException extends GlobalException {
    public UserNumberAlreadyExistsException() {
        super(ErrorCode.USER_NUMBER_ALREADY_EXISTS);
    }
}
