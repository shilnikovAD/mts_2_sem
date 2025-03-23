package model;

import org.example.Main;
import org.example.controller.UserBookController;
import org.example.model.UserBook;
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
public class UserBookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserBookService userBookService;

  private static final String JWT_TOKEN = "Bearer your_jwt_token";

  @Test
  public void testGetAllBooks() throws Exception {
    when(userBookService.getAllBooks()).thenReturn(List.of(
        new UserBook(1L, "Java Basics", "John Doe"),
        new UserBook(2L, "Spring Boot", "Jane Doe")
    ));

    mockMvc.perform(get("/user-books")
            .header("Authorization", JWT_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().json("""
                        [
                            {"id":1,"title":"Java Basics","author":"John Doe"},
                            {"id":2,"title":"Spring Boot","author":"Jane Doe"}
                        ]
                    """));
  }

  @Test
  public void testGetBookById_Positive() throws Exception {
    when(userBookService.getBookById(1L))
        .thenReturn(Optional.of(new UserBook(1L, "Book Title", "Author Name")));


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
