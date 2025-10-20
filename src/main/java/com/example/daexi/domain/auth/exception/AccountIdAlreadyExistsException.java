package com.example.daexi.domain.auth.exception;

import com.example.daexi.global.exception.ErrorCode;
import com.example.daexi.global.exception.GlobalException;

public class AccountIdAlreadyExistsException extends GlobalException {
  public AccountIdAlreadyExistsException() {
    super(ErrorCode.ACCOUNT_ID_ALREADY_EXISTS);
  }
}
