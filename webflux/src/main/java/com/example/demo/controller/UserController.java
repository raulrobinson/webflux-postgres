// controller/UserController.java
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User Management", description = "Operations related to User management")
public class UserController {

    private final UserService service;

    @GetMapping
    @Operation(summary = "Get All Users", description = "Retrieves a list of all users.")
    public Flux<User> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by ID", description = "Retrieves a user by their ID.")
    @Parameter(name = "id", description = "ID of the user to retrieve", required = true, example = "1")
    public Mono<User> get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create User", description = "Creates a new user in the system.")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update User", description = "Updates an existing user in the system.")
    @Parameter(name = "id", description = "ID of the user to update", required = true, example = "1")
    public Mono<User> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User", description = "Deletes a user from the system.")
    @Parameter(name = "id", description = "ID of the user to delete", required = true, example = "1")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    // 🔹 Nuevo: paginación
    @GetMapping("/paged")
    @Operation(summary = "Get Paged Users", description = "Retrieves a paginated list of users.")
    @Parameter(name = "page", description = "Page number (0-based)", example = "0")
    @Parameter(name = "size", description = "Number of users per page", example = "5")
    public Flux<User> paged(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size) {
        return service.findAllPaged(page, size);
    }

    // 🔹 Nuevo: filtro por email
    @GetMapping("/search/email")
    @Operation(summary = "Search Users by Email", description = "Searches users by their email address.")
    @Parameter(name = "email", description = "Email address to search for", required = true, example = "raul@test.com")
    public Flux<User> searchByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }

    // 🔹 Nuevo: filtro por nombre (like)
    @GetMapping("/search/name")
    @Operation(summary = "Search Users by Name", description = "Searches users by their name (partial match).")
    @Parameter(name = "name", description = "Name to search for (partial match)", required = true, example = "Raul")
    public Flux<User> searchByName(@RequestParam String name) {
        return service.findByNameLike(name);
    }

}
