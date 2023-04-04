package com.example.security.book;

import com.example.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllById(Long id);
    List<Book> findAllByUser(User user);
}
