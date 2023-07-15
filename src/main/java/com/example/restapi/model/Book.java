package com.example.restapi.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookID;
    private String title, author;
    @Column(length = 3000)
    private String synopsis;
    private Integer publishedYear, numberOfPages;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    public Book() {

    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
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

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Book:" + "\n" +
                "Id: " + this.bookID + "\n" +
                "Title: " + this.title + "\n" +
                "Author: " + this.author + "\n" +
                "Genre: " + this.genre.toString() + "\n" +
                "Synopsis: " + this.synopsis + "\n" +
                "Published Year: " + this.publishedYear + "\n" +
                "Number of Pages: " + this.numberOfPages + "\n";
    }
}
