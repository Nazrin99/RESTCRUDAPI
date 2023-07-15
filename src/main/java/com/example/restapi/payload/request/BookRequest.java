package com.example.restapi.payload.request;

import com.example.restapi.model.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookRequest {
    @NotNull
    @NotBlank
    private String title, author, synopsis;

    @NotBlank
    @NotNull
    private Integer publishedYear, numberOfPages;

    @NotBlank
    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
