package com.forum.microservice.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.forum.microservice.search.entity.UserEntity;
import com.forum.microservice.search.exceptions.UserNotFoundException;
import com.forum.microservice.search.service.UserService;
import com.forum.microservice.search.service.SystemEventClient;
import java.util.List;

/////////////

@RestController
@RequestMapping("/search")
public class UserController {
    private UserService userService;

    private SystemEventClient eventClient;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public UserEntity getUser(@PathVariable int userId) {
        UserEntity user = userService.findById(userId);

        if (user == null) {
            throw new UserNotFoundException("User id not found: " + userId);
        }
        eventClient.postEvent("vrijemetest","servistest","usertest","actiontest","resourcetest","responsetest");
        return user;
    }

    @PostMapping("/users")
    public UserEntity addUser(@RequestBody UserEntity user) {
        user.setId(0);
        return userService.save(user);
    }

    @PutMapping("/users")
    public UserEntity updateUser(@RequestBody UserEntity user) {
        return userService.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int userId) {
        UserEntity tempUser = userService.findById(userId);

        if (tempUser == null) {
            throw new UserNotFoundException("User id not found: " + userId);
        }

        userService.deleteById(userId);
    }
}

