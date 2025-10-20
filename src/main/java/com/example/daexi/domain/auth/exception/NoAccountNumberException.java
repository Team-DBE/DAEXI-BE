package com.example.daexi.domain.auth.exception;

import com.example.daexi.global.exception.ErrorCode;
import com.example.daexi.global.exception.GlobalException;

public class NoAccountNumberException extends GlobalException {
    public NoAccountNumberException() {
        super(ErrorCode.NO_ACCOUNT_NUMBER);
    }
}
