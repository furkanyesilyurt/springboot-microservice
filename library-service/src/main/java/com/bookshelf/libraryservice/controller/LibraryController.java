package com.bookshelf.libraryservice.controller;

import com.bookshelf.libraryservice.dto.AddBookRequest;
import com.bookshelf.libraryservice.dto.LibraryDto;
import com.bookshelf.libraryservice.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

    Logger logger = LoggerFactory.getLogger(LibraryController.class);

    private final LibraryService libraryService;
    private final Environment environment;

    public LibraryController(LibraryService libraryService, Environment environment) {
        this.libraryService = libraryService;
        this.environment = environment;
    }

    @GetMapping
    public ResponseEntity<List<Long>> getAllLibraries() {
        logger.info("log");
        return new ResponseEntity<>(libraryService.getAllLibraries(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        logger.info("Library created on port number:" + environment.getProperty("local.server.port"));
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
