package com.example.rguide.web.controllers;

import com.example.rguide.domain.models.User;
import com.example.rguide.service.RecipeService;
import com.example.rguide.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final RecipeService recipeService;

    @GetMapping("/{id}")
    public String getProfilePage(Model model,@PathVariable Long id, HttpServletRequest req) {
        User user = this.userService.findById(id).orElseThrow();
        User me = (User) userService.loadUserByUsername(req.getRemoteUser());
        boolean doIFollow = false;
        boolean isMe = false;
        if(me.getFollowingUsers().contains(user)){
            doIFollow = true;
        }
        if(me == user){
            isMe = true;
        }

        model.addAttribute("recipes", recipeService.recipesOfUser(user));
        model.addAttribute("user", user);
        model.addAttribute("doIFollow", doIFollow);
        model.addAttribute("isMe", isMe);
        return "profile";
    }

    @PostMapping("/follow/{id}")
    public String followUser(Model model, @PathVariable Long id, HttpServletRequest req){
        User userToFollow = this.userService.findById(id).orElseThrow();
        User me = (User) userService.loadUserByUsername(req.getRemoteUser());
        this.userService.followUser(me, userToFollow);
        return "redirect:/user/" + id;
    }
    @PostMapping("/unfollow/{id}")
    public String unFollowUser(Model model, @PathVariable Long id, HttpServletRequest req){
        User userToUnFollow = this.userService.findById(id).orElseThrow();
        User me = (User) userService.loadUserByUsername(req.getRemoteUser());
        this.userService.unFollowUser(me, userToUnFollow);
        return "redirect:/user/" + id;
    }
}
