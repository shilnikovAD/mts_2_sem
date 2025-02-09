package org.example.Main.controller;

import org.example.Main.service.UserBookService;
import org.example.Main.model.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-books")
public class UserBookController {

  @Autowired
  private UserBookService userBookService;

  @GetMapping
  public List<UserBook> getAllBooks() {
    return userBookService.getAllBooks();
  }

  @PostMapping
  public UserBook createBook(@RequestBody UserBook userBook) {
    return userBookService.createBook(userBook);
  }
}
