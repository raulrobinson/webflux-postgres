// service/UserService.java
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final DatabaseClient client; // para queries custom con paginación

    public Flux<User> findAll() {
        return repository.findAll();
    }

    public Mono<User> findById(Long id) {
        return repository.findById(id);
    }

    public Mono<User> save(User user) {
        return repository.save(user);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    // Paginación manual
    public Flux<User> findAllPaged(int page, int size) {
        int offset = page * size;
        String query = "SELECT * FROM users ORDER BY id LIMIT " + size + " OFFSET " + offset;
        return client.sql(query).map((row, meta) -> {
            User u = new User();
            u.setId(row.get("id", Long.class));
            u.setName(row.get("name", String.class));
            u.setEmail(row.get("email", String.class));
            return u;
        }).all();
    }

    // Filtrar por email
    public Flux<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    // Filtrar por nombre (like)
    public Flux<User> findByNameLike(String name) {
        return repository.findByNameLike(name);
    }
}
