package com.lidia.organization.exception;

public class EmailNotUniqueException extends RuntimeException {

    private static final String MESSAGE = "Email must be unique!";

    public EmailNotUniqueException() {
        super(MESSAGE);
    }
}
