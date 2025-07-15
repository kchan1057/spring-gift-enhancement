package com.example.demo.repository.user;

import com.example.demo.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  void deleteByEmail(String email);
  boolean existsByEmail(String email);
}
