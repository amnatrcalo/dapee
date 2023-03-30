package com.forum.microservice.postcomment.service;


import com.forum.microservice.postcomment.entity.UserEntity;
import com.forum.microservice.postcomment.exceptions.UserNotFoundException;
import com.forum.microservice.postcomment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }

  @Override
  public UserEntity findById(int id) {
    Optional<UserEntity> foundEntity = userRepository.findById(id);
    if (foundEntity.isPresent()) {
      return foundEntity.get();
    } else {
      throw new UserNotFoundException("User id not found: " + id);
    }
  }

  @Override
  public UserEntity save(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  @Override
  public void deleteById(int id) {
    userRepository.deleteById(id);
  }
}
