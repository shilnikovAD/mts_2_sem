package org.example.controller;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.dto.UserDto;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  private final RateLimiter rateLimiter = RateLimiter.ofDefaults("userController");

  @GetMapping
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetAllUsers")
  public List<UserDto> getAllUsers() {
    return rateLimiter.executeSupplier(() -> userService.getAllUsers());
  }

  @GetMapping("/{id}")
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetUserById")
  public UserDto getUserById(@PathVariable Long id) {
    return rateLimiter.executeSupplier(() -> userService.getUserById(id));
  }

  @PostMapping
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackCreateUser")
  public UserDto createUser(@RequestBody User user) {
    return rateLimiter.executeSupplier(() -> userService.createUser(user));
  }

  @PutMapping("/{id}")
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackUpdateUser")
  public UserDto updateUser(@PathVariable Long id, @RequestBody User user) {
    return rateLimiter.executeSupplier(() -> userService.updateUser(id, user));
  }

  @PatchMapping("/{id}")
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackPatchUser")
  public UserDto patchUser(@PathVariable Long id, @RequestBody User user) {
    return rateLimiter.executeSupplier(() -> userService.patchUser(id, user));
  }

  @DeleteMapping("/{id}")
  @CircuitBreaker(name = "userService", fallbackMethod = "fallbackDeleteUser")
  public void deleteUser(@PathVariable Long id) {
    rateLimiter.executeRunnable(() -> userService.deleteUser(id));
  }

  public List<UserDto> fallbackGetAllUsers(Throwable throwable) {
    return List.of();
  }

  public UserDto fallbackGetUserById(Long id, Throwable throwable) {
    return new UserDto(id, "Unknown", "Unknown");
  }

  public UserDto fallbackCreateUser(User user, Throwable throwable) {
    return new UserDto(-1L, "Fallback", "Fallback");
  }

  public UserDto fallbackUpdateUser(Long id, User user, Throwable throwable) {
    return new UserDto(id, "Fallback", "Fallback");
  }

  public UserDto fallbackPatchUser(Long id, User user, Throwable throwable) {
    return new UserDto(id, "Fallback", "Fallback");
  }

  public void fallbackDeleteUser(Long id, Throwable throwable) {}
}
