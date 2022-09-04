package com.example.rguide.service;

import com.example.rguide.domain.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, String image);
    User login(String username, String password);
    Optional<User> findById(Long id);
    void followUser(User me, User userToFollow);
    void unFollowUser(User me, User userToUnFollow);
}
