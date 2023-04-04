package com.example.security.book;

import com.example.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String book;
    @ManyToMany(mappedBy = "books")
    private List<User> user;

    public Book(String book) {
        this.book = book;
    }
}
