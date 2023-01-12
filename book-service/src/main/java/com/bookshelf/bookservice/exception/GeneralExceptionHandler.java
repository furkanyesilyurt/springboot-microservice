package com.bookshelf.bookservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(BookCouldNotCreateException.class)
    public ResponseEntity<?> handle(BookNotFoundException bookNotFoundException) {
        return new ResponseEntity<>(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookCouldNotCreateException.class)
    public ResponseEntity<?> handle(BookCouldNotCreateException bookCouldNotCreateException) {
        return new ResponseEntity<>(bookCouldNotCreateException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
