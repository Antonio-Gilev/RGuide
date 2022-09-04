package com.example.rguide.web.controllers;

import com.example.rguide.domain.models.Recipe;
import com.example.rguide.service.RecipeService;
import com.example.rguide.service.StepService;
import com.example.rguide.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/explore")
@AllArgsConstructor
public class ExploreController {
    private final RecipeService recipeService;
    private final StepService stepService;
    private final UserService userService;


    @GetMapping
    public String getExplorePage(Model model, HttpServletRequest req, @RequestParam(required = false) String search) {
        List<Recipe> recipes;
        if(search == null){
            recipes = this.recipeService.findAll();
        }
        else {
            recipes = this.recipeService.searchRecipes(search);
        }
        model.addAttribute("recipes", recipes);
        return "explore";
    }
}
