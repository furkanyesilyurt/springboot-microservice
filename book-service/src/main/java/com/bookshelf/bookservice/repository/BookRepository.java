package com.bookshelf.bookservice.repository;

import com.bookshelf.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBarcode(String barcode);
}
