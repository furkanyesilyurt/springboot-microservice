package com.bookshelf.libraryservice.service;

import com.bookshelf.libraryservice.client.BookServiceClient;
import com.bookshelf.libraryservice.dto.AddBookRequest;
import com.bookshelf.libraryservice.dto.LibraryDto;
import com.bookshelf.libraryservice.exception.LibraryNotFoundException;
import com.bookshelf.libraryservice.model.Library;
import com.bookshelf.libraryservice.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    public LibraryService(LibraryRepository libraryRepository,  BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public List<Long> getAllLibraries() {
        return libraryRepository.findAll()
                .stream()
                .map(Library::getId)
                .collect(Collectors.toList());
    }

    public LibraryDto getAllBooksInLibraryById(Long libraryId) {

        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: {}" + libraryId));

        return LibraryDto.builder()
                .id(library.getId())
                .userBooks(library.getUserBooks()
                        .stream()
                        .map(id -> bookServiceClient.getBookById(id).getBody())//feign
                        .collect(Collectors.toList()))
                .build();
    }

    public LibraryDto createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return LibraryDto.builder()
                .id(newLibrary.getId())
                .build();
    }

    public void addBookToLibrary(AddBookRequest addBookRequest) {
        Long bookId = bookServiceClient.getBookByBarcode(addBookRequest.getBarcode()).getBody().getId();
        Library library = libraryRepository.findById(addBookRequest.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: {}" + addBookRequest.getId()));

        library.getUserBooks().add(bookId);

        libraryRepository.save(library);
    }
}
