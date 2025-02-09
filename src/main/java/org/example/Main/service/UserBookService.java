package org.example.Main.service;

import org.example.Main.model.UserBook;
import org.example.Main.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookService {

  @Autowired
  private UserBookRepository userBookRepository;

  public List<UserBook> getAllBooks() {
    return userBookRepository.findAll();
  }

  public UserBook createBook(UserBook userBook) {
    return userBookRepository.save(userBook);
  }
}
