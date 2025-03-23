package general;

import org.example.Main;
import org.example.model.UserBook;
import org.example.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Main.class, SecurityConfig.class})
@ActiveProfiles("test")
public class UserBookControllerE2ETest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testGetAllBooks() {
    String url = String.format("http://localhost:%d/user-books", port);

    UserBook[] testBooks = new UserBook[]{
        new UserBook(1L, "Java Basics", "John Doe"),
        new UserBook(2L, "Spring Boot", "Jane Doe")
    };

    for (UserBook book : testBooks) {
      restTemplate.postForEntity(url, book, UserBook.class);
    }

    ResponseEntity<UserBook[]> response = restTemplate.getForEntity(url, UserBook[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected HTTP status OK (200)");
    assertNotNull(response.getBody(), "Response body should not be null");
    assertTrue(response.getBody().length > 0,
        "The response body should contain at least one book.");
  }
}

