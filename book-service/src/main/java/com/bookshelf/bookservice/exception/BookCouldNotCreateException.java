package com.bookshelf.bookservice.exception;

public class BookCouldNotCreateException extends RuntimeException {
    public BookCouldNotCreateException(String message) {
        super(message);
    }
}
