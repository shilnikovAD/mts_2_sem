package org.example.actuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class ActuatorCustomEndpoint {

  @GetMapping("/admin/uuid")
  public String getUUID() {
    return UUID.randomUUID().toString();
  }
}
