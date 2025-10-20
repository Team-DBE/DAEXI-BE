package com.example.daexi.domain.room.exception;

import com.example.daexi.global.exception.ErrorCode;
import com.example.daexi.global.exception.GlobalException;

public class PartyPasswordIncorrectException extends GlobalException {
    public PartyPasswordIncorrectException() {
        super(ErrorCode.PARTY_PASSWORD_INCORRECT);
    }
}
