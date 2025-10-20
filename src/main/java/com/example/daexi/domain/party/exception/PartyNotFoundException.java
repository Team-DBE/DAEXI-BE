package com.example.daexi.domain.party.exception;

import com.example.daexi.global.exception.ErrorCode;
import com.example.daexi.global.exception.GlobalException;

public class PartyNotFoundException extends GlobalException {
    public PartyNotFoundException(String message) {
        super(ErrorCode.PARTY_NOT_FOUND);
    }
}
