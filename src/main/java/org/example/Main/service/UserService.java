package org.example.Main.service;

import org.example.Main.model.User;
import org.example.Main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    logger.info("Fetching all users");
    return userRepository.findAll();
  }

  public User getUserById(Long id) {
    logger.info("Fetching user with ID: {}", id);
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
  }

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
