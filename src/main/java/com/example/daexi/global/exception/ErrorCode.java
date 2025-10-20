package com.example.daexi.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "Account not found"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Invalid password"),
    NO_ACCOUNT_NUMBER(HttpStatus.BAD_REQUEST, "No account number"),
    USER_NUMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "User number already exists"),
    PARTY_NOT_FOUND(HttpStatus.NOT_FOUND, "Party not found"),
    PARTY_PASSWORD_INCORRECT(HttpStatus.BAD_REQUEST, "Incorrect password"),
    USER_DELETION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "User deletion failed"),
    ACCOUNT_ID_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Account id already exists"),;


    private final HttpStatus httpStatus;

    private final String message;

}
