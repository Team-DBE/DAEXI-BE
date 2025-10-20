package com.example.daexi.domain.user.exception;

import com.example.daexi.global.exception.ErrorCode;
import com.example.daexi.global.exception.GlobalException;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
