package com.bookshelf.libraryservice.controller;

import com.bookshelf.libraryservice.dto.AddBookRequest;
import com.bookshelf.libraryservice.dto.LibraryDto;
import com.bookshelf.libraryservice.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<List<Long>> getAllLibraries() {
        return new ResponseEntity<>(libraryService.getAllLibraries(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        return new ResponseEntity<>(libraryService.createLibrary(), HttpStatus.CREATED);
    }

    @GetMapping("/libraryId/{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable Long id) {
        return new ResponseEntity<>(libraryService.getAllBooksInLibraryById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> addBookToLibarary(@RequestBody AddBookRequest addBookRequest) {
        libraryService.addBookToLibrary(addBookRequest);
        return ResponseEntity.ok().build();
    }
}
