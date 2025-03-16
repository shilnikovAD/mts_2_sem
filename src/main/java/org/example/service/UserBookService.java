package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.UserBook;
import org.example.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class UserBookService {

  @Autowired
  private UserBookRepository userBookRepository;

  public List<UserBook> getAllBooks() {
    return userBookRepository.findAll();
  }

  public Optional<UserBook> getBookById(Long id) {
    return userBookRepository.findById(id);
  }

  public UserBook createBook(UserBook userBook) {
    return userBookRepository.save(userBook);
  }

  public UserBook updateBook(Long id, UserBook userBook) {
    if (userBookRepository.findById(id).isPresent()) {
      return userBookRepository.save(userBook);
    }
    throw new RuntimeException("Book not found");
  }

  public void deleteBook(Long id) {
    userBookRepository.deleteById(id);
  }

  public UserBook patchBook(Long id, UserBook userBookDetails) {
    UserBook existingUserBook = userBookRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book not found"));

    if (userBookDetails.getTitle() != null) {
      existingUserBook.setTitle(userBookDetails.getTitle());
    }
    if (userBookDetails.getAuthor() != null) {
      existingUserBook.setAuthor(userBookDetails.getAuthor());
    }

    return userBookRepository.save(existingUserBook);
  }
}
