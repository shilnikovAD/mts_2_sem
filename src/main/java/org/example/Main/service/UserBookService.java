package org.example.Main.service;

import org.example.Main.model.UserBook;
import org.example.Main.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UserBookService {

  @Autowired
  private UserBookRepository userBookRepository;

  public List<UserBook> getAllBooks() {
    return userBookRepository.findAll();
  }

  public Optional<UserBook> getBookById(Long id) {
    return userBookRepository.findById(id);  // Возвращаем Optional
  }

  public UserBook createBook(UserBook userBook) {
    return userBookRepository.save(userBook);
  }

  public UserBook updateBook(Long id, UserBook userBook) {
    if (!userBookRepository.existsById(id)) {
      throw new RuntimeException("Book not found");
    }
    userBook.setId(id);
    return userBookRepository.save(userBook);
  }

  public UserBook patchBook(Long id, UserBook userBook) {
    if (!userBookRepository.existsById(id)) {
      throw new RuntimeException("Book not found");
    }
    userBook.setId(id);
    return userBookRepository.save(userBook);
  }

  public void deleteBook(Long id) {
    if (userBookRepository.existsById(id)) {
      userBookRepository.deleteById(id);
    } else {
      throw new RuntimeException("Book not found");
    }
  }
}
