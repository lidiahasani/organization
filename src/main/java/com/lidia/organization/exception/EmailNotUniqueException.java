package com.lidia.organization.exception;

public class EmailNotUniqueException extends RuntimeException {

    private static final String MESSAGE = "Punonjesi me kete email ekziston.";

    public EmailNotUniqueException() {
        super(MESSAGE);
    }
}
