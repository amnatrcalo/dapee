package com.forum.microservice.administration.repository;

import com.forum.microservice.administration.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {}
