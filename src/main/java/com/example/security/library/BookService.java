package com.example.security.library;

import com.example.security.book.Book;
import com.example.security.book.BookRepository;
import com.example.security.user.User;
import com.example.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    UserRepository repository;
    @Autowired
    BookRepository bookRepository;

    public ResponseEntity<?> buyBook(BuyBookRequest request ) {
        try {
            Book book = bookRepository.findById(request.getBookId()).orElseThrow();
            List<User> users = book.getUser();
            users.add(repository.findById(request.getUserId()).orElseThrow());
            book.setUser(users);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<?> addBook(Book book) {
        try {
            bookRepository.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public List<Book> seeBook(Long id) {
        return bookRepository.findAllById(id);
    }
    public String getBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return book.getBook();
    }

}
