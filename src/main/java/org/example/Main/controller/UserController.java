package org.example.Main.controller;

import org.example.Main.service.UserService;
import org.example.Main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public String getAllUsers() {
    return "testUser";
  }

}
