package org.example.aspect;

import org.example.Main;
import org.example.aspect.ControllerAspect;
import org.example.config.SecurityConfig;
import org.example.config.TestContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {Main.class, SecurityConfig.class})
public class ControllerAspectTest extends TestContainerConfig {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ControllerAspect controllerAspect;

  @Test
  public void testCounterIncrements() throws Exception {
    assertEquals(0, controllerAspect.getCounter());

    mockMvc.perform(get("/users")).andExpect(status().isOk());
    assertEquals(2, controllerAspect.getCounter());

    mockMvc.perform(get("/users")).andExpect(status().isOk());
    assertEquals(4, controllerAspect.getCounter());
  }
}
