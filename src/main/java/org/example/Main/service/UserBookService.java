package org.example.Main.service;

import org.example.Main.model.UserBook;
import org.example.Main.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserBookService {

  private static final Logger logger = LoggerFactory.getLogger(UserBookService.class);

  @Autowired
  private UserBookRepository userBookRepository;

  public List<UserBook> getAllBooks() {
    logger.info("Fetching all books");
    return userBookRepository.findAll();
  }

  public UserBook createBook(UserBook userBook) {
    logger.info("Creating new book: {}", userBook.getTitle());
    return userBookRepository.save(userBook);
  }
}
