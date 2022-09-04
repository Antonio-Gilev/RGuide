package com.example.rguide.service.impl;

import com.example.rguide.domain.models.Recipe;
import com.example.rguide.domain.models.User;
import com.example.rguide.repository.RecipeRepository;
import com.example.rguide.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> findAll() {
        return this.recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return this.recipeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> findByFollowingUsers(List<User> followingUsers) {
        List<Recipe> allRecipes = this.findAll();
        List<Recipe> recipesOfFollowingUsers = new ArrayList<>();
        for (User user :
                followingUsers) {
            recipesOfFollowingUsers = allRecipes.stream().filter(z->z.getUser() == user).collect(Collectors.toList());

        }
        return recipesOfFollowingUsers;
    }

    @Override
    public List<Recipe> recipesOfUser(User user){
        return this.recipeRepository.findAllByUser(user);
    }

    @Override
    public List<Recipe> searchRecipes(String search) {
        return this.recipeRepository.findAllByNameContainsIgnoreCase(search);
    }

    @Override
    public Optional<Recipe> save(String name, User user, String ingredients, String image) {
        Recipe recipe = new Recipe(name, user, ingredients, image);
        this.recipeRepository.save(recipe);
        return Optional.of(recipe);
    }

    @Override
    public Optional<Recipe> edit(Long id, String name, User user, String ingredients) {
        Recipe recipe = this.findById(id).orElseThrow();
        recipe.setIngredients(ingredients);
        recipe.setName(name);
        recipe.setUser(user);
        return Optional.of(recipe);
    }

    @Override
    public void UpVote(Long id) {
        Recipe recipe = this.findById(id).orElseThrow();
        recipe.setLikes(recipe.getLikes() + 1);
    }

    @Override
    public void DownVote(Long id) {
        Recipe recipe = this.findById(id).orElseThrow();
        recipe.setLikes(recipe.getLikes() - 1);
    }


}
