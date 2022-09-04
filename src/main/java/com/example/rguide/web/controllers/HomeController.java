package com.example.rguide.web.controllers;

import com.example.rguide.domain.models.Recipe;
import com.example.rguide.domain.models.User;
import com.example.rguide.service.RecipeService;
import com.example.rguide.service.StepService;
import com.example.rguide.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/", "/home"})
@AllArgsConstructor
public class HomeController {

    private final RecipeService recipeService;
    private final StepService stepService;
    private final UserService userService;


    @GetMapping
    public String getHomePage(Model model, HttpServletRequest req) {
        User user = (User) userService.loadUserByUsername(req.getRemoteUser());
        model.addAttribute("recipes", recipeService.findByFollowingUsers(user.getFollowingUsers()));
        model.addAttribute("steps", stepService.findAll());
        return "home";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "accessDenied";
    }

    @GetMapping("/add")
    public String addRecipePage(Model model){
        return "addRecipe";
    }

    @GetMapping("/addStep")
    public String addStepPage(Model model){
        return "addNextStep";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam String name,
            @RequestParam String ingredients,
            @RequestParam String image,
            Authentication authentication, HttpServletRequest request) {
        User user = (User) authentication.getPrincipal();

        Recipe recipe = this.recipeService.save(name, user, ingredients, image).orElseThrow();
        request.getSession().setAttribute("Recipe", recipe);

        return "redirect:/addStep";
    }

    @PostMapping("/addStep")
    public String saveStep(
            @RequestParam String image,
            @RequestParam String description,
            Authentication authentication, HttpServletRequest request) {
        User user = (User) authentication.getPrincipal();
        Recipe recipe = (Recipe) request.getSession().getAttribute("Recipe");
        this.stepService.save(description, image, recipe);
        return "redirect:/addStep";
    }
}
