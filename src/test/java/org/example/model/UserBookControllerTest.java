package org.example.model;

import org.example.Main;
import org.example.config.TestContainerConfig;
import org.example.controller.UserBookController;
import org.example.config.SecurityConfig;
import org.example.service.UserBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserBookController.class)
@ContextConfiguration(classes = {SecurityConfig.class, Main.class})
public class UserBookControllerTest extends TestContainerConfig {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserBookService userBookService;

  private static final String JWT_TOKEN = "Bearer your_jwt_token";

  @Test
  public void testGetAllBooks() throws Exception {
    when(userBookService.getAllBooks()).thenReturn(List.of(
        new UserBook(1L, "Java Basics", "John Doe", 1L),
        new UserBook(2L, "Spring Boot", "Jane Doe", 1L)
    ));

    mockMvc.perform(get("/user-books")
            .header("Authorization", JWT_TOKEN))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].title").value("Java Basics"))
        .andExpect(jsonPath("$[0].author").value("John Doe"))
        .andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].title").value("Spring Boot"))
        .andExpect(jsonPath("$[1].author").value("Jane Doe"));
  }

  @Test
  public void testGetBookById_Positive() throws Exception {
    when(userBookService.getBookById(1L))
        .thenReturn(Optional.of(new UserBook(1L, "Book Title", "Author Name", 1L)));

    mockMvc.perform(get("/user-books/1")
            .header("Authorization", JWT_TOKEN))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.title").value("Book Title"))
        .andExpect(jsonPath("$.author").value("Author Name"));
  }

  @Test
  public void testGetBookById_Negative() throws Exception {
    when(userBookService.getBookById(999L)).thenReturn(Optional.empty());

    mockMvc.perform(get("/user-books/999")
            .header("Authorization", JWT_TOKEN))
        .andExpect(status().isNotFound());
  }
}
