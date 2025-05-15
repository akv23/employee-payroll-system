package com.payroll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoJobInfoFoundException extends RuntimeException {
    public NoJobInfoFoundException(String message) {
        super(message);
    }
}
