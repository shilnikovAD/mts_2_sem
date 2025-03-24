package org.example.service;

import org.example.dto.UserDto;
import org.example.exception.CustomRetryException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;

  /**
   * Повторяем запрос, если возникают временные ошибки с базой данных.
   */
  @Retryable(
      value = {CustomRetryException.class, TransientDataAccessException.class},
      maxAttempts = 5,
      backoff = @Backoff(delay = 5000)
  )
  public List<UserDto> getAllUsers() {
    logger.info("Fetching all users");
    return userRepository.findAll().stream()
        .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
        .collect(Collectors.toList());
  }

  @Cacheable(value = "users", key = "#id")
  public UserDto getUserById(Long id) {
    logger.info("Fetching user with ID: {}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          logger.error("User not found with id: {}", id);
          return new RuntimeException("User not found with id: " + id);
        });
    return new UserDto(user.getId(), user.getName(), user.getEmail());
  }

  @Transactional
  @CachePut(value = "users", key = "#result.id")
  public UserDto createUser(User user) {
    logger.info("Creating new user: {}", user.getName());
    User savedUser = userRepository.save(user);
    return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
  }

  @Transactional
  public UserDto updateUser(Long id, User userDetails) {
    logger.info("Updating user with ID: {}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          logger.error("User not found with id: {}", id);
          return new RuntimeException("User not found with id: " + id);
        });

    user.setName(userDetails.getName());
    user.setEmail(userDetails.getEmail());

    User updatedUser = userRepository.save(user);
    return new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
  }

  @Transactional
  public UserDto patchUser(Long id, User userDetails) {
    logger.info("Partially updating user with ID: {}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          logger.error("User not found with id: {}", id);
          return new RuntimeException("User not found with id: " + id);
        });

    if (userDetails.getName() != null) {
      user.setName(userDetails.getName());
    }
    if (userDetails.getEmail() != null) {
      user.setEmail(userDetails.getEmail());
    }

    User patchedUser = userRepository.save(user);
    return new UserDto(patchedUser.getId(), patchedUser.getName(), patchedUser.getEmail());
  }

  @Transactional
  public void deleteUser(Long id) {
    logger.info("Deleting user with ID: {}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          logger.error("User not found with id: {}", id);
          return new RuntimeException("User not found with id: " + id);
        });

    userRepository.delete(user);
    logger.info("User with ID: {} deleted successfully", id);
  }
}
