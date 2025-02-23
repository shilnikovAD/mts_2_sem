package org.example.OpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.UserBook;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book API", description = "Operations related to books")
public interface BOOK_API {

  @Operation(summary = "Get all books", description = "Retrieves a list of all books")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved books"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping
  List<UserBook> getAllBooks();

  @Operation(summary = "Get book by ID", description = "Retrieves a book by its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Book found"),
      @ApiResponse(responseCode = "404", description = "Book not found")
  })
  @GetMapping("/{id}")
  UserBook getBookById(@Parameter(description = "ID of the book") @PathVariable Long id);

  @Operation(summary = "Create a new book", description = "Adds a new book to the database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Book created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid request data")
  })
  @PostMapping
  UserBook createBook(@RequestBody UserBook userBook);

  @Operation(summary = "Update book", description = "Updates an existing book")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Book updated successfully"),
      @ApiResponse(responseCode = "404", description = "Book not found")
  })
  @PutMapping("/{id}")
  UserBook updateBook(@Parameter(description = "ID of the book") @PathVariable Long id, @RequestBody UserBook userBook);

  @Operation(summary = "Partially update book", description = "Applies partial updates to a book")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Book updated successfully"),
      @ApiResponse(responseCode = "404", description = "Book not found")
  })
  @PatchMapping("/{id}")
  UserBook patchBook(@Parameter(description = "ID of the book") @PathVariable Long id, @RequestBody UserBook userBook);

  @Operation(summary = "Delete book", description = "Removes a book by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Book not found")
  })
  @DeleteMapping("/{id}")
  void deleteBook(@Parameter(description = "ID of the book") @PathVariable Long id);
}
