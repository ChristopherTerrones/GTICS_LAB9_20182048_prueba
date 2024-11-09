package com.example.prueba_lab9.repository;

import com.example.prueba_lab9.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    boolean existsByMealId(Integer mealId);
}
