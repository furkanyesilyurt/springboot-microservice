package com.bookshelf.bookservice.repository;

import com.bookshelf.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
