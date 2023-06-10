package com.forum.microservice.notification.repository;

import com.forum.microservice.notification.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {}
