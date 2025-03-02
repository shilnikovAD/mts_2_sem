package org.example.repository;

import org.example.model.UserBook;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserBookRepository {

  private List<UserBook> books = new ArrayList<>();

  public List<UserBook> findAll() {
    return books;
  }

  public Optional<UserBook> findById(Long id) {
    return books.stream()
        .filter(book -> book.getId().equals(id))
        .findFirst();
  }

  public UserBook save(UserBook userBook) {
    books.add(userBook);
    return userBook;
  }

  public void deleteById(Long id) {
    books.removeIf(book -> book.getId().equals(id));
  }
}
