package com.example.security.library;

import com.example.security.book.Book;
import com.example.security.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class MainController {
  final BookService service;
  final UserService userService;

  public MainController(BookService service, UserService userService) {
    this.service = service;
    this.userService = userService;
  }

  @PostMapping("/addNewUser")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<?> addUser(@RequestBody User user) {
   return userService.addUser(user);
  }
  @PostMapping("/addNewBook")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<?> addBook(@RequestBody Book book) {
    return service.addBook(book);
  }
  @PostMapping("/buyBook")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<?> buyBook(@RequestBody BuyBookRequest request) {
   return service.buyBook(request);
  }
  @PostMapping("/seeAllBooks")
  @PreAuthorize("hasAuthority('USER')")
  public List<Book> seeBook(Long id) {
    return service.seeBook(id);
  }
  @PostMapping("/getBook")
  @PreAuthorize("hasAuthority('USER')")
  public String getBook(Long id) {
    return service.getBook(id);
  }

  @GetMapping("/me")
  public List<Book> getCurrentUser(String token) {
    return userService.getUserByToken(token);
  }
}
