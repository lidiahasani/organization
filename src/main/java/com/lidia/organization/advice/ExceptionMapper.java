package com.lidia.organization.advice;

import com.lidia.organization.exception.EmailNotUniqueException;
import com.lidia.organization.exception.EntityNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(EntityNotExistsException.class)
    public ResponseEntity<String> handleException(EntityNotExistsException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(EmailNotUniqueException.class)
    public ResponseEntity<String> handleException(EmailNotUniqueException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
