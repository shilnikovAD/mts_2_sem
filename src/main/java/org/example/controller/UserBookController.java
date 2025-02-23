package org.example.controller;

import org.example.OpenApi.BOOK_API;
import org.example.service.UserBookService;
import org.example.model.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-books")
public class UserBookController implements BOOK_API {

  @Autowired
  private UserBookService userBookService;

  @Override
  public List<UserBook> getAllBooks() {
    return userBookService.getAllBooks();
  }

  @Override
  public UserBook getBookById(Long id) {
    return userBookService.getBookById(id)
        .orElseThrow(() -> new RuntimeException("Book not found"));
  }

  @Override
  public UserBook createBook(UserBook userBook) {
    return userBookService.createBook(userBook);
  }

  @Override
  public UserBook updateBook(Long id, UserBook userBook) {
    return userBookService.updateBook(id, userBook);
  }

  @Override
  public UserBook patchBook(Long id, UserBook userBook) {
    return userBookService.patchBook(id, userBook);
  }

  @Override
  public void deleteBook(Long id) {
    userBookService.deleteBook(id);
  }
}
