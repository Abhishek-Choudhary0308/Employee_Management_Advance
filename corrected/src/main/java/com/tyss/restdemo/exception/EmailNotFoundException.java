package com.tyss.restdemo.exception;

public class EmailNotFoundException extends EmployeeNotFoundException {

    public EmailNotFoundException(String message) {
        super(message);
    }
}

