package com.bookshelf.bookservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private BookIdDto bookIdDto;
    private String title;
    private Integer bookYear;
    private String author;
    private String pressName;
}
