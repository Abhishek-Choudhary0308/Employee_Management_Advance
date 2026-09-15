package com.tyss.restdemo.exception;

public class BusinessWarningException extends RuntimeException {
    public BusinessWarningException(String message) {
        super(message);
    }
}
