package com.example.restapi.repository;

import com.example.restapi.model.Book;
import com.example.restapi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // Search for book by title
    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    List<Book> getBooksByTitle(String title);

    // Search for book by author
    @Query("SELECT b FROM Book b WHERE b.author = ?1")
    List<Book> getBooksByAuthor(String author);

    // Search by published year
    @Query("SELECT b FROM Book b WHERE b.publishedYear = ?1")
    List<Book> getBooksByPublishedYear(Integer publishedDate);

    // Search within year range
    @Query("SELECT b FROM Book b WHERE b.publishedYear >= ?1 and b.publishedYear <= ?2")
    List<Book> getBooksByPublishedYearIsBetween(Integer startYear, Integer endYear);

    // Search for book before a specified year
    @Query("SELECT b FROM Book b WHERE b.publishedYear < ?1")
    List<Book> getBooksByPublishedYearBefore(Integer publishedYear);

    // Search for book after a specified year
    @Query("SELECT b FROM Book b WHERE b.publishedYear > ?1")
    List<Book> getBooksByPublishedYearAfter(Integer publishedYear);

    // Search for book by genre
    @Query("SELECT b FROM Book b WHERE b.genre = ?1")
    List<Book> getBooksByGenre(Genre genre);

    @Query("DELETE FROM Book b WHERE b.title = ?1")
    void deleteByTitle(String title);

    @Query("DELETE FROM Book b WHERE b.author = ?1")
    void deleteByAuthor(String author);
}
