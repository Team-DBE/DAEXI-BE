package com.example.daexi.domain.user.exception;

import com.example.daexi.global.exception.ErrorCode;
import com.example.daexi.global.exception.GlobalException;

public class UserDeletionFailedException extends GlobalException {
    public UserDeletionFailedException() {
        super(ErrorCode.USER_DELETION_FAILED);
    }
}
