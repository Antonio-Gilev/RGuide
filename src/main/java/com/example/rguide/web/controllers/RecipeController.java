package com.example.rguide.web.controllers;

import com.example.rguide.domain.models.Recipe;
import com.example.rguide.domain.models.User;
import com.example.rguide.service.RecipeService;
import com.example.rguide.service.StepService;
import com.example.rguide.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/recipe")
@AllArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final StepService stepService;
    private final UserService userService;


    @GetMapping("/{id}")
    public String getRecipePage(Model model, HttpServletRequest req, @PathVariable Long id) {
        Recipe recipe = recipeService.findById(id).orElseThrow();
        model.addAttribute("recipe", recipe);
        return "recipe";
    }
}
