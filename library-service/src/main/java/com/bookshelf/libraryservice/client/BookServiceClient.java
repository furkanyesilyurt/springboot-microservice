package com.bookshelf.libraryservice.client;

import com.bookshelf.libraryservice.dto.BookDto;
import com.bookshelf.libraryservice.dto.BookIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "book-service", path = "/api/v1/book")
public interface BookServiceClient {

    @GetMapping
    ResponseEntity<List<BookDto>> getAllBooks();

    @GetMapping("/barcode/{barcode}")
    ResponseEntity<BookIdDto> getBookByBarcode(@PathVariable String barcode);

    @GetMapping("/id/{id}")
    ResponseEntity<BookDto> getBookById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<BookIdDto> saveBook(@RequestBody BookDto bookDto);
}
