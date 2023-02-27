package com.lidia.organization.exception;

public class EmailNotUniqueException extends RuntimeException{

    public EmailNotUniqueException(String message) {
        super(message);
    }
}
