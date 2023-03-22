package com.forum.microservice.search.dao;

import com.forum.microservice.search.entity.UserEntity;
public interface UserDAO {
    void save(UserEntity userEntity);
}
