package com.example.rguide.service;

import com.example.rguide.domain.models.Recipe;
import com.example.rguide.domain.models.Step;
import com.example.rguide.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface StepService {
    List<Step> findAll();

    List<Step> findAllByRecipeId(Long id);

    Optional<Step> findById(Long id);

    Optional<Step> save(String description, String image, Recipe recipe);

    Optional<Step> edit(Long id, String description, String image, Recipe recipe);

    void deleteById(Long id);
}
