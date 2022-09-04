package com.example.rguide.service.impl;

import com.example.rguide.domain.models.Recipe;
import com.example.rguide.domain.models.Step;
import com.example.rguide.repository.StepRepository;
import com.example.rguide.service.RecipeService;
import com.example.rguide.service.StepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StepServiceImpl implements StepService {

    private final StepRepository stepRepository;
    private final RecipeService recipeService;

    @Override
    public List<Step> findAll() {
        return this.stepRepository.findAll();
    }

    @Override
    public List<Step> findAllByRecipeId(Long id) {
        Recipe recipe = recipeService.findById(id).orElseThrow();

        return this.stepRepository.findAllByRecipe(recipe);
    }

    @Override
    public Optional<Step> findById(Long id) {
        return this.stepRepository.findById(id);
    }

    @Override
    public Optional<Step> save(String description, String image, Recipe recipe) {
        Step step = new Step(description, image, recipe);
        this.stepRepository.save(step);
        return Optional.of(step);
    }

    @Override
    public Optional<Step> edit(Long id, String description, String image, Recipe recipe) {
        Step step = this.findById(id).orElseThrow();
        step.setDescription(description);
        step.setImage(image);
        step.setRecipe(recipe);
        return Optional.of(step);
    }

    @Override
    public void deleteById(Long id) {
        this.stepRepository.deleteById(id);
    }
}
