package com.forum.microservice.administration.dao;

import com.forum.microservice.administration.entity.UserEntity;

public interface UserDao {
  void save(UserEntity userEntity);
}
