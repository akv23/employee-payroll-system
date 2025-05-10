package com.payroll.exception;

public class NoJobInfoFoundException extends RuntimeException {
    public NoJobInfoFoundException(String message) {
        super(message);
    }
}
