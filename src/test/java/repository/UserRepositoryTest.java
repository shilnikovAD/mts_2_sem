package repository;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
public class UserRepositoryTest {

  @Container
  public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
      .withDatabaseName("testdb")
      .withUsername("testuser")
      .withPassword("testpass");

  @Autowired
  private UserRepository userRepository;

  @Test
  public void testSaveUser() {
    User user = new User();
    user.setName("Test User");
    user.setEmail("testuser@example.com");

    User savedUser = userRepository.save(user);

    assertNotNull(savedUser.getId());
    assertEquals("Test User", savedUser.getName());
    assertEquals("testuser@example.com", savedUser.getEmail());
  }
}
