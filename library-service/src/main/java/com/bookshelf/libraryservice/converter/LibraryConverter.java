package com.bookshelf.libraryservice.converter;

import com.bookshelf.libraryservice.dto.LibraryDto;
import com.bookshelf.libraryservice.model.Library;
import org.springframework.stereotype.Service;

@Service
public class LibraryConverter {

    public LibraryDto convertLibraryToLibraryDto(Library library) {
        return LibraryDto.builder()
                .build();
    }
}
