package com.example.daexi.domain.auth.exception;

public class AccountIdAlreadyExistsException extends RuntimeException {
  public AccountIdAlreadyExistsException() {
    super("Account id already exists");
  }
}
