package com.forum.microservice.search.service;

import com.forum.microservice.search.entity.UserEntity;
import java.util.List;

public interface UserService {
    List<UserEntity> findAll();

    UserEntity findById(int id);

    UserEntity save(UserEntity userEntity);

    void deleteById(int id);
}
