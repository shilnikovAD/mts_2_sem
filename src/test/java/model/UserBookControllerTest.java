package model;

import org.example.Main.controller.UserController;
import org.example.Main.model.User;
import org.example.Main.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)  // Тестируем только контроллер
public class UserBookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean  // Мокаем сервис
  private UserService userService;

  @Test
  public void testGetAllUsers() throws Exception {
    mockMvc.perform(get("/users"))
        .andExpect(status().isOk())  // Проверяем, что ответ 200
        .andExpect(content().string("testUser")); // Проверяем содержимое
  }

  @Test
  public void testGetUserById_Positive() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setName("John Doe");

    when(userService.getUserById(1L)).thenReturn(user);

    mockMvc.perform(get("/users/1"))
        .andExpect(status().isOk())  // Проверяем, что ответ 200
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("John Doe"));
  }

  @Test
  public void testGetUserById_Negative() throws Exception {
    when(userService.getUserById(999L)).thenReturn(null);

    mockMvc.perform(get("/users/999"))
        .andExpect(status().isNotFound());  // Ожидаем 404
  }
}
