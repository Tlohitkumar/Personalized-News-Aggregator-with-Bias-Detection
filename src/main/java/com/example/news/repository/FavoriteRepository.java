package com.example.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.news.model.Favorite;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserEmail(String userEmail);
}