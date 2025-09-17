// repository/UserRepository.java
package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    // Filtrar por email exacto
    Flux<User> findByEmail(String email);

    // Filtrar por nombre con like
    @Query("SELECT * FROM users WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Flux<User> findByNameLike(String name);
}
