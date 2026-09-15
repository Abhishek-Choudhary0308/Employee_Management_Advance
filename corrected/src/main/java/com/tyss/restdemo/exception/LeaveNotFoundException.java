package com.tyss.restdemo.exception;

public class LeaveNotFoundException extends RuntimeException {
    public LeaveNotFoundException(String message) {
        super(message);
    }
}
