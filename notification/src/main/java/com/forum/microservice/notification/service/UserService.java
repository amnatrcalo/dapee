package com.forum.microservice.notification.service;

import com.forum.microservice.notification.entity.UserEntity;
import java.util.List;

public interface UserService {
  List<UserEntity> findAll();

  UserEntity findById(int id);

  UserEntity save(UserEntity userEntity);

  void deleteById(int id);
}
