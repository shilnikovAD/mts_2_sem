package org.example.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.config.TestContainerConfig;
import java.util.Optional;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestContainerConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest extends TestContainerConfig {

  @Autowired
  private UserRepository userRepository;

  @Test
  void testSaveAndFindUser() {
    User user = new User();
    user.setName("testuser");
    user.setEmail("testuser@example.com");
    userRepository.save(user);

    Optional<User> foundUser = userRepository.findByEmail("testuser@example.com");
    assertTrue(foundUser.isPresent());
    assertEquals("testuser", foundUser.get().getName());
  }
}
