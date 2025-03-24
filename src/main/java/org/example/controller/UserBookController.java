package org.example.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.example.OpenApi.BOOK_API;
import org.example.service.UserBookService;
import org.example.model.UserBook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-books")
@RequiredArgsConstructor
public class UserBookController implements BOOK_API {

  private final UserBookService userBookService;

  private final RateLimiter rateLimiter = RateLimiter.ofDefaults("bookService");
  private final CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("bookService");

  @Override
  @GetMapping
  public List<UserBook> getAllBooks() {
    return circuitBreaker.executeSupplier(() ->
        rateLimiter.executeSupplier(() -> userBookService.getAllBooks())
    );
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<UserBook> getBookById(@PathVariable Long id) {
    return circuitBreaker.executeSupplier(() ->
        rateLimiter.executeSupplier(() -> userBookService.getBookById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()))
    );
  }

  @Override
  @PostMapping
  public UserBook createBook(@RequestBody UserBook userBook) {
    return circuitBreaker.executeSupplier(() ->
        rateLimiter.executeSupplier(() -> userBookService.createBook(userBook))
    );
  }

  @Override
  @PutMapping("/{id}")
  public UserBook updateBook(@PathVariable Long id, @RequestBody UserBook userBook) {
    return circuitBreaker.executeSupplier(() ->
        rateLimiter.executeSupplier(() -> userBookService.updateBook(id, userBook))
    );
  }

  @Override
  @PatchMapping("/{id}")
  public UserBook patchBook(@PathVariable Long id, @RequestBody UserBook userBook) {
    return circuitBreaker.executeSupplier(() ->
        rateLimiter.executeSupplier(() -> userBookService.patchBook(id, userBook))
    );
  }

  @Override
  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    circuitBreaker.executeRunnable(() ->
        rateLimiter.executeRunnable(() -> userBookService.deleteBook(id))
    );
  }
}
