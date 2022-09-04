package com.example.rguide.repository;

import com.example.rguide.domain.models.Recipe;
import com.example.rguide.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByUser (User user);
    List<Recipe> findAllByNameContainsIgnoreCase(String search);
}
