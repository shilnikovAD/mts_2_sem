package general;

import org.example.Main;
import org.example.model.UserBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserBookControllerE2ETest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testGetAllBooks() {
    String url = String.format("http://localhost:%d/users/books", port);

    ResponseEntity<UserBook[]> response = restTemplate.getForEntity(url, UserBook[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertNotNull(response.getBody());

    assertTrue(response.getBody().length > 0, "The response body should contain at least one book.");
  }
}
