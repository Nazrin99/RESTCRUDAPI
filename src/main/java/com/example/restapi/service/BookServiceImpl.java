package com.example.restapi.service;

import com.example.restapi.model.Book;
import com.example.restapi.model.Genre;
import com.example.restapi.payload.request.BookRequest;
import com.example.restapi.payload.response.MessageResponse;
import com.example.restapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;

    @Override
    public MessageResponse addBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setGenre(bookRequest.getGenre());
        book.setSynopsis(bookRequest.getSynopsis());
        book.setPublishedYear(bookRequest.getPublishedYear());
        book.setNumberOfPages(bookRequest.getNumberOfPages());
        bookRepository.save(book);
        return new MessageResponse("Book saved into repo successfully");
    }

    @Override
    public Optional<Book> updateBookDetails(Integer bookID, BookRequest bookRequest) {
        Optional<Book> book = bookRepository.findById(bookID);
        if (book.isEmpty()) {
            return book;
        }
        else {
            book.get().setTitle(bookRequest.getTitle());
            book.get().setAuthor(bookRequest.getAuthor());
            book.get().setSynopsis(bookRequest.getSynopsis());
            book.get().setGenre(bookRequest.getGenre());
            book.get().setPublishedYear(bookRequest.getPublishedYear());
            book.get().setNumberOfPages(bookRequest.getNumberOfPages());
            bookRepository.save(book.get());
            return book;
        }
    }

    @Override
    public void deleteBook(Integer bookID) {
        Optional<Book> book = bookRepository.findById(bookID);
        if (book.isPresent() && book.get().getBookID().equals(bookID)) {
            bookRepository.deleteById(bookID);
        }
        else throw new NoSuchElementException();
    }

    @Override
    public Book getBookByID(Integer bookID) {
        return bookRepository.findById(bookID).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.getBooksByTitle(title);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByPublishedYear(Integer publishedYear) {
        return bookRepository.getBooksByPublishedYear(publishedYear);
    }

    @Override
    public List<Book> getBooksByPublishedYearIsBetween(Integer startYear, Integer endYear) {
        return bookRepository.getBooksByPublishedYearIsBetween(startYear, endYear);
    }

    @Override
    public List<Book> getBooksByPublishedYearBefore(Integer publishedYear) {
        return bookRepository.getBooksByPublishedYearBefore(publishedYear);
    }

    @Override
    public List<Book> getBooksByPublishedYearAfter(Integer publishedYear) {
        return bookRepository.getBooksByPublishedYearAfter(publishedYear);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return bookRepository.getBooksByGenre(genre);
    }

    @Override
    public void deleteByAuthor(String author) {
        List<Book> toDelete = bookRepository.getBooksByAuthor(author);
        if (toDelete != null) {
            for (Book book: toDelete) {
                bookRepository.deleteById(book.getBookID());
            }
        }
    }

    @Override
    public void deleteByTitle(String title) {
        List<Book> toDelete = bookRepository.getBooksByTitle(title);
        if (toDelete != null) {
            for (Book book: toDelete) {
                bookRepository.deleteById(book.getBookID());
            }
        }
    }
}
