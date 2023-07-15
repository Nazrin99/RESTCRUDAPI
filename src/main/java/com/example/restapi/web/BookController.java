package com.example.restapi.web;

import com.example.restapi.model.Book;
import com.example.restapi.model.Genre;
import com.example.restapi.payload.request.BookRequest;
import com.example.restapi.payload.response.MessageResponse;
import com.example.restapi.service.BookService;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."),
}
)
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addBook(@RequestBody BookRequest bookRequest) {
        MessageResponse messageResponse = bookService.addBook(bookRequest);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateBookDetails(@RequestParam Integer id, @RequestBody BookRequest bookRequest) {
        Optional<Book> book = bookService.updateBookDetails(id, bookRequest);
        if (book.isPresent()) {
            return new ResponseEntity<>(new MessageResponse("Details of book id: " + id +  " updated"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new MessageResponse("Targeted book with id: " + id + " does not exist!"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteBook(@PathVariable("id") Integer bookID) {
        bookService.deleteBook(bookID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable("id") Integer bookID) {
        return new ResponseEntity<>(bookService.getBookByID(bookID), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBooksByParameter(
            @RequestParam Optional<String> title,
            @RequestParam Optional<String> author
            ) {
        if (title.isPresent()) {
            bookService.deleteByTitle(title.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else if (author.isPresent()) {
            bookService.deleteByAuthor(author.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Parameters does not follow guidelines", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<?> getBooksByParameter(
            @RequestParam Optional<String> title,
            @RequestParam Optional<String> author,
            @RequestParam Optional<Integer> beforeYear,
            @RequestParam Optional<Integer> afterYear,
            @RequestParam Optional<Integer> startYear,
            @RequestParam Optional<Integer> endYear,
            @RequestParam Optional<Integer> publishedYear,
            @RequestParam Optional<String> genre) {
        if (title.isPresent()) {
            return new ResponseEntity<>(bookService.getBooksByTitle(title.get()), HttpStatus.OK);
        }
        else if (author.isPresent()) {
            return new ResponseEntity<>(bookService.getBooksByAuthor(author.get()), HttpStatus.OK);
        }
        else if (beforeYear.isPresent()) {
            return new ResponseEntity<>(bookService.getBooksByPublishedYearBefore(beforeYear.get()), HttpStatus.OK);
        }
        else if (afterYear.isPresent()) {
            return new ResponseEntity<>(bookService.getBooksByPublishedYearAfter(afterYear.get()), HttpStatus.OK);
        }
        else if (startYear.isPresent() && endYear.isPresent()) {
            return new ResponseEntity<>(bookService.getBooksByPublishedYearIsBetween(startYear.get(), endYear.get()), HttpStatus.OK);
        }
        else if (publishedYear.isPresent()) {
            return new ResponseEntity<>(bookService.getBooksByPublishedYear(publishedYear.get()), HttpStatus.OK);
        }
        else if (genre.isPresent()) {
            return new ResponseEntity<>(bookService.getBooksByGenre(Genre.valueOf(genre.get())), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new MessageResponse("Parameters does not follow guidelines"), HttpStatus.BAD_REQUEST);
        }
    }
}