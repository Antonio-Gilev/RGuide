package com.example.rguide.service;

import com.example.rguide.domain.models.Recipe;
import com.example.rguide.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> findAll();

    Optional<Recipe> findById(Long id);

    void deleteById(Long id);

    List<Recipe> findByFollowingUsers(List<User> followingUsers);

    Optional<Recipe> save(String name, User user, String ingredients, String image);

    Optional<Recipe> edit(Long id, String name, User user, String ingredients);

    void UpVote(Long id);

    void DownVote(Long id);

    List<Recipe> recipesOfUser(User user);

    List<Recipe> searchRecipes(String search);
}
