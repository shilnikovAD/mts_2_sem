package general;

import org.example.Main;
import org.example.config.SecurityConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SecurityConfig.class, Main.class})
@ActiveProfiles("test")
public class TestPostgresqlContainerTest {

  private static final Logger logger = LoggerFactory.getLogger(TestPostgresqlContainerTest.class);

  private static PostgreSQLContainer<?> postgresContainer;

  @BeforeAll
  public static void setUp() {
    postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
        .withDatabaseName("testdb")
        .withUsername("testuser")
        .withPassword("testpass")
        .withInitScript("init.sql");

    postgresContainer.start();

    String host = postgresContainer.getHost();
    String port = String.valueOf(postgresContainer.getMappedPort(5432));
    String url = "jdbc:postgresql://" + host + ":" + port + "/testdb";

    logger.info("PostgreSQL container is running on host: {}, port: {}", host, port);
    logger.info("Connection URL: {}", url);
  }

  @Test
  public void testDatabaseConnection() {

    logger.info("Running database test...");
  }

  @AfterAll
  public static void tearDown() {
    if (postgresContainer != null) {
      postgresContainer.stop();
    }
  }
}
