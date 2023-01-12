package com.bookshelf.bookservice.converter;

import com.bookshelf.bookservice.dto.BookDto;
import com.bookshelf.bookservice.dto.BookIdDto;
import com.bookshelf.bookservice.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookConverter {

    public BookDto convertBookToBookDto(Book book) {
        return BookDto.builder()
                .bookIdDto(BookIdDto.builder().id(book.getId()).barcode(book.getBarcode()).build())
                .title(book.getTitle())
                .bookYear(book.getBookYear())
                .author(book.getAuthor())
                .pressName(book.getPressName())
                .build();
    }

    public Book convertBookDtoToBook(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getBookIdDto().getId())
                .author(bookDto.getAuthor())
                .bookYear(bookDto.getBookYear())
                .title(bookDto.getTitle())
                .pressName(bookDto.getPressName())
                .barcode(bookDto.getBookIdDto().getBarcode())
                .build();
    }

    public BookIdDto convertBookToBookIdDto(Book book) {
        return BookIdDto.builder()
                .id(book.getId())
                .barcode(book.getBarcode())
                .build();
    }
}
