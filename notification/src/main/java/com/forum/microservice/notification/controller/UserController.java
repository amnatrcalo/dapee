package com.forum.microservice.notification.controller;

import com.forum.microservice.notification.entity.UserEntity;
import com.forum.microservice.notification.exceptions.UserNotFoundException;
import com.forum.microservice.notification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class UserController {
  private UserService userService;

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
