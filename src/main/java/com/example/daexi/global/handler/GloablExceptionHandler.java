package com.example.daexi.global.handler;

import com.example.daexi.global.exception.GlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GloablExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<String> handleGloablException(GlobalException e) {
        return new ResponseEntity<>(e.getMessage(), e.getErrorCode().getHttpStatus());
    }

}
