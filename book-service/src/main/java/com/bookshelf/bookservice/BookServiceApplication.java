package com.bookshelf.bookservice;

import com.bookshelf.bookservice.model.Book;
import com.bookshelf.bookservice.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BookServiceApplication implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BookServiceApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = Book.builder().title("Book1").bookYear(2001).author("Author1").pressName("Press1").barcode("1001").build();
        Book book2 = Book.builder().title("Book2").bookYear(2002).author("Author2").pressName("Press2").barcode("1002").build();
        Book book3 = Book.builder().title("Book3").bookYear(2003).author("Author3").pressName("Press3").barcode("1003").build();
        Book book4 = Book.builder().title("Book4").bookYear(2004).author("Author4").pressName("Press4").barcode("1004").build();

        List<Book> books = Arrays.asList(book1, book2, book3, book4);
        bookRepository.saveAll(books);

        System.out.println(books.toString());
    }
}
