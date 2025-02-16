package org.example.Main.OpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.Main.model.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "User API", description = "Operations related to users")
public interface USER_API {

  @Operation(summary = "Get all users", description = "Retrieves a list of all users")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping
  List<User> getAllUsers();

  @Operation(summary = "Get user by ID", description = "Retrieves a user by their ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User found"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @GetMapping("/{id}")
  User getUserById(@Parameter(description = "ID of the user") @PathVariable Long id);

  @Operation(summary = "Create a new user", description = "Adds a new user to the database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid request data")
  })
  @PostMapping
  User createUser(@RequestBody User user);

  @Operation(summary = "Update user", description = "Updates an existing user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User updated successfully"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @PutMapping("/{id}")
  User updateUser(@Parameter(description = "ID of the user") @PathVariable Long id, @RequestBody User user);

  @Operation(summary = "Partially update user", description = "Applies partial updates to a user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User updated successfully"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @PatchMapping("/{id}")
  User patchUser(@Parameter(description = "ID of the user") @PathVariable Long id, @RequestBody User user);

  @Operation(summary = "Delete user", description = "Removes a user by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "User deleted successfully"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @DeleteMapping("/{id}")
  void deleteUser(@Parameter(description = "ID of the user") @PathVariable Long id);
}
