package com.bookshelf.libraryservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "library")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Library {

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "generator", sequenceName = "ID_USER_SEQ")
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<Long> userBooks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<Long> userBooks) {
        this.userBooks = userBooks;
    }
}