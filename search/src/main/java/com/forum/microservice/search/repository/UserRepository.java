package com.forum.microservice.search.repository;

import com.forum.microservice.search.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {}