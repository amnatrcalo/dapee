package com.forum.microservice.administration.service;

import com.forum.microservice.administration.entity.UserEntity;
import java.util.List;

public interface UserService {
  List<UserEntity> findAll();

  UserEntity findById(int id);

  UserEntity save(UserEntity userEntity);

  void deleteById(int id);
}
