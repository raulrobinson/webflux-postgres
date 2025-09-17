// controller/UserController.java
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public Flux<User> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    public Mono<User> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
