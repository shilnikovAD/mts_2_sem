package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.OpenApi.BOOK_API;
import org.example.service.UserBookService;
import org.example.model.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/user-books")
@RequiredArgsConstructor
public class UserBookController implements BOOK_API {

  @Autowired
  private UserBookService userBookService;

  @Override
  @GetMapping
  public List<UserBook> getAllBooks() {
    return userBookService.getAllBooks();
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<UserBook> getBookById(@PathVariable Long id) {
    return userBookService.getBookById(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Override
  @PostMapping
  public UserBook createBook(@RequestBody UserBook userBook) {
    return userBookService.createBook(userBook);
  }

  @Override
  @PutMapping("/{id}")
  public UserBook updateBook(@PathVariable Long id, @RequestBody UserBook userBook) {
    return userBookService.updateBook(id, userBook);
  }

  @Override
  @PatchMapping("/{id}")
  public UserBook patchBook(@PathVariable Long id, @RequestBody UserBook userBook) {
    return userBookService.patchBook(id, userBook);
  }

  @Override
  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    userBookService.deleteBook(id);
  }
}
