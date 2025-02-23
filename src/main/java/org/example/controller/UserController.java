package org.example.controller;

import org.example.OpenApi.USER_API;
import org.example.service.UserService;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements USER_API {

  @Autowired
  private UserService userService;

  @Override
  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @Override
  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @Override
  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @Override
  @PutMapping("/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User user) {
    return userService.updateUser(id, user);
  }

  @Override
  @PatchMapping("/{id}")
  public User patchUser(@PathVariable Long id, @RequestBody User user) {
    return userService.patchUser(id, user);
  }

  @Override
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }
}
