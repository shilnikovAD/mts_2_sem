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

  public User createUser(User user) {
    logger.info("Creating new user: {}", user.getName());
    return userRepository.save(user);
  }
}
