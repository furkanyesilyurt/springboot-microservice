package com.bookshelf.bookservice.controller;

import com.bookshelf.bookservice.dto.BookDto;
import com.bookshelf.bookservice.dto.BookIdDto;
import com.bookshelf.bookservice.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<BookIdDto> getBookByBarcode(@PathVariable @NotEmpty String barcode) {
        return new ResponseEntity<>(bookService.findByBarcode(barcode), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable @NotEmpty Long id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.saveBook(bookDto), HttpStatus.CREATED);
    }
}
