package com.example.restapi.service;

import com.example.restapi.model.Book;
import com.example.restapi.model.Genre;
import com.example.restapi.payload.request.BookRequest;
import com.example.restapi.payload.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {

    // Add book
    MessageResponse addBook(BookRequest bookRequest);

    // Update book details
    Optional<Book> updateBookDetails(Integer bookID, BookRequest bookRequest);

    // Delete book
    void deleteBook(Integer bookID);

    // Get book by ID
    Book getBookByID(Integer bookID);

    // Get all books
    List<Book> getAllBooks();

    // Get books by title
    List<Book> getBooksByTitle(String title);

    // Get books by author
    List<Book> getBooksByAuthor(String author);

    // Get books by published year
    List<Book> getBooksByPublishedYear(Integer publishedYear);

    // Get books within year range
    List<Book> getBooksByPublishedYearIsBetween(Integer startYear, Integer endYear);

    // Get books before a specified year
    List<Book> getBooksByPublishedYearBefore(Integer publishedYear);

    // Get books after a specified year
    List<Book> getBooksByPublishedYearAfter(Integer publishedYear);

    // Get books by genre
    List<Book> getBooksByGenre(Genre genre);

    // Delete book by author
    void deleteByAuthor(String author);

    // Delete book by title
    void deleteByTitle(String title);
}
