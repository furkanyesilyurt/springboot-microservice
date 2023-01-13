package com.bookshelf.libraryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenerealExceptionHandler {

    @ExceptionHandler(LibraryNotFoundException.class)
    public ResponseEntity<?> handle(LibraryNotFoundException bookNotFoundException) {
        return new ResponseEntity<>(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
