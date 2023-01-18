package com.bookshelf.libraryservice.client;

import com.bookshelf.libraryservice.dto.BookDto;
import com.bookshelf.libraryservice.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@FeignClient(name = "book-service", path = "/api/v1/book")
public interface BookServiceClient {

    Logger logger = LoggerFactory.getLogger(BookServiceClient.class);

    @GetMapping
    ResponseEntity<List<BookDto>> getAllBooks();

    @GetMapping("/barcode/{barcode}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker", fallbackMethod = "getBookFallback")
    ResponseEntity<BookIdDto> getBookByBarcode(@PathVariable String barcode);

    default ResponseEntity<BookIdDto> getBookFallback(String isbn, Exception exception) {
        logger.info("Book not found by isbn " + isbn + ", returning default BookDto object");
        return ResponseEntity.ok(BookIdDto.builder()
                .barcode("default-isbn")
                .build());
    }

    @GetMapping("/id/{id}")
    @CircuitBreaker(name = "getBookByIdCircuitBreaker", fallbackMethod = "getBookByIdFallback")
    ResponseEntity<BookDto> getBookById(@PathVariable Long id);

    default ResponseEntity<BookDto> getBookByIdFallback(String bookId, Exception exception) {
        logger.info("Book not found by id " + bookId + ", returning default BookDto object");
        BookIdDto bookIdDto = BookIdDto.builder()
                .barcode("default-isbn")
                .build();
        return ResponseEntity.ok(BookDto.builder()
                .bookIdDto(bookIdDto)
                .build());
    }

    @PostMapping
    ResponseEntity<BookIdDto> saveBook(@RequestBody BookDto bookDto);
}
