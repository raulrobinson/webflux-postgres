// repository/UserRepository.java
package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
