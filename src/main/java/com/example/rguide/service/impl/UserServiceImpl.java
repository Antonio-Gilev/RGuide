package com.example.rguide.service.impl;

import com.example.rguide.domain.exceptions.*;
import com.example.rguide.domain.models.User;
import com.example.rguide.repository.UserRepository;
import com.example.rguide.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, String image) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname, image);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public void followUser(User me, User userToFollow) {
        me.getFollowingUsers().add(userToFollow);
        this.userRepository.save(me);
    }

    @Override
    public void unFollowUser(User me, User userToUnFollow) {
        me.getFollowingUsers().remove(userToUnFollow);
        this.userRepository.save(me);
    }
}
