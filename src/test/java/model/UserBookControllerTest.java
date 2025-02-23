package model;

import java.util.List;
import org.example.controller.UserController;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@SpringBootTest
public class UserBookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  public void testGetAllUsers() throws Exception {
    when(userService.getAllUsers()).thenReturn(List.of(new User("Artur", "testUser")));

    mockMvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(content().string("testUser"));
  }

  @Test
  public void testGetUserById_Positive() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setName("John Doe");

    when(userService.getUserById(1L)).thenReturn(user);

    mockMvc.perform(get("/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("John Doe"));
  }

  @Test
  public void testGetUserById_Negative() throws Exception {
    when(userService.getUserById(999L)).thenReturn(null);

    mockMvc.perform(get("/users/999"))
        .andExpect(status().isNotFound());
  }
}
