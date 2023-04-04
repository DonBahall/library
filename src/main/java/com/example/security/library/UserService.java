package com.example.security.library;

import com.example.security.book.Book;
import com.example.security.book.BookRepository;
import com.example.security.user.User;
import com.example.security.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    BookRepository bookRepository;
    @Value("${SECRET_KEY}")
    private String SECRET_KEY ;

    public ResponseEntity<?> addUser(User user) {
        try {
            repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<Book> getUserByToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();

        User user = repository.findByEmail(email).orElseThrow();
        return bookRepository.findAllByUser(user);
    }
}
