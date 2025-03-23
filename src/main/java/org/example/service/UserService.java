package org.example.service;

import org.example.exception.CustomRetryException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;

  /**
   * Гарантия: обеспечиваем **устойчивость выполнения метода** при временных сбоях,
   * автоматически повторяя попытки выполнения до 5 раз с задержкой 10 секунд между попытками.
   * Это позволяет минимизировать влияние кратковременных проблем с внешними сервисами, например, с базой данных.
   */


  @Retryable(
      value = CustomRetryException.class,
      maxAttempts = 5,
      backoff = @Backoff(delay = 10000)
  )
  public List<User> getAllUsers() {
    logger.info("Fetching all users");
    return userRepository.findAll();
  }

  @Cacheable(value = "users", key = "#id")
  public User getUserById(Long id) {
    logger.info("Fetching user with ID: {}", id);
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
  }

  @CachePut(value = "users", key = "#result.id")
  public User createUser(User user) {
    logger.info("Creating new user: {}", user.getName());
    return userRepository.save(user);
  }

  public User updateUser(Long id, User userDetails) {
    logger.info("Updating user with ID: {}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    user.setName(userDetails.getName());
    user.setEmail(userDetails.getEmail());

    return userRepository.save(user);
  }

  public User patchUser(Long id, User userDetails) {
    logger.info("Partially updating user with ID: {}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    if (userDetails.getName() != null) {
      user.setName(userDetails.getName());
    }
    if (userDetails.getEmail() != null) {
      user.setEmail(userDetails.getEmail());
    }

    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    logger.info("Deleting user with ID: {}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    userRepository.delete(user);
  }
}
