package org.example.repository;

import org.example.model.UserBook;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class UserBookRepository {

  private final List<UserBook> books = new ArrayList<>();
  private final WebClient webClient;
  private final String[] urls = {
      "https://jsonplaceholder.typicode.com/posts/1",
      "https://jsonplaceholder.typicode.com/posts/2",
      "https://jsonplaceholder.typicode.com/posts/3"
  };

  public UserBookRepository(WebClient webClient) {
    this.webClient = webClient;
  }

  public List<UserBook> findAll() {
    return books;
  }

  public Optional<UserBook> findById(Long id) {
    String randomUrl = urls[new Random().nextInt(urls.length)];

    String response = webClient.get()
        .uri(randomUrl)
        .retrieve()
        .bodyToMono(String.class)
        .block(); // ⚡️ Блокирующий вызов (ждет ответа)

    System.out.println("Response from external API: " + response);

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
