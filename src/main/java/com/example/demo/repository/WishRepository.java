package com.example.demo.repository;

import com.example.demo.entity.Wish;
import com.example.demo.entity.WishId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, WishId> {

  List<Wish> findAllById_UserId(Long userId);
}
