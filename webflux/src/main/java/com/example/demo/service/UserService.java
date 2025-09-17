// service/UserService.java
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

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
}
