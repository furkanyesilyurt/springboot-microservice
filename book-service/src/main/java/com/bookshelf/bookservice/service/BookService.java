package com.bookshelf.bookservice.service;

import com.bookshelf.bookservice.converter.BookConverter;
import com.bookshelf.bookservice.dto.BookDto;
import com.bookshelf.bookservice.dto.BookIdDto;
import com.bookshelf.bookservice.exception.BookCouldNotCreateException;
import com.bookshelf.bookservice.exception.BookNotFoundException;
import com.bookshelf.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public BookService(BookRepository bookRepository, BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream().map(bookConverter::convertBookToBookDto)
                .collect(Collectors.toList());
    }

    public BookIdDto findByBarcode(String barcode) {
        return bookRepository.findByBarcode(barcode)
                .map(bookConverter::convertBookToBookIdDto)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by barcode: {}" + barcode));
    }

    public BookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(bookConverter::convertBookToBookDto)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by id: {}" + id));
    }

    public BookDto saveBook(BookDto bookDto) {
        return bookRepository.save(bookConverter.convertBookDtoToBook(bookDto))
                .map(bookConverter::convertBookToBookDto)
                .orElseThrow(() -> new BookCouldNotCreateException("Book could not save: {}" + bookDto));
    }
}
