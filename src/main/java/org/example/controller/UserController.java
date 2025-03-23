package org.example.controller;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.OpenApi.USER_API;
import org.example.service.UserService;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;

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

@RestController
@RequestMapping("/users")
public class UserController implements USER_API {

  @Autowired
  private UserService userService;

  private final RateLimiter rateLimiter = RateLimiter.ofDefaults("userController");

  @Override
  @GetMapping
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetAllUsers")
  public List<User> getAllUsers() {
    return rateLimiter.executeSupplier(() -> userService.getAllUsers());
  }

  @Override
  @GetMapping("/{id}")
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetUserById")
  public User getUserById(@PathVariable Long id) {
    return rateLimiter.executeSupplier(() -> userService.getUserById(id));
  }

  @Override
  @PostMapping
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackCreateUser")
  public User createUser(@RequestBody User user) {
    return rateLimiter.executeSupplier(() -> userService.createUser(user));
  }

  @Override
  @PutMapping("/{id}")
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackUpdateUser")
  public User updateUser(@PathVariable Long id, @RequestBody User user) {
    return rateLimiter.executeSupplier(() -> userService.updateUser(id, user));
  }

  @Override
  @PatchMapping("/{id}")
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackPatchUser")
  public User patchUser(@PathVariable Long id, @RequestBody User user) {
    return rateLimiter.executeSupplier(() -> userService.patchUser(id, user));
  }

  @Override
  @DeleteMapping("/{id}")
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackDeleteUser")
  public void deleteUser(@PathVariable Long id) {
    rateLimiter.executeRunnable(() -> userService.deleteUser(id));
  }
}
