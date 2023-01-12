package com.bookshelf.bookservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "generator", sequenceName = "ID_USER_SEQ")
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    private Long id = null;
    private String title;
    private Integer bookYear;
    private String author;
    private String pressName;
    private String barcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBookYear() {
        return bookYear;
    }

    public void setBookYear(String bookYear) {
        this.bookYear = Integer.valueOf(bookYear);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPressName() {
        return pressName;
    }

    public void setPressName(String pressName) {
        this.pressName = pressName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bookYear=" + bookYear +
                ", author='" + author + '\'' +
                ", pressName='" + pressName + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
