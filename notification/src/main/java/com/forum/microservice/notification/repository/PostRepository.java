package com.forum.microservice.notification.repository;

import java.util.List;

import com.forum.microservice.notification.entity.PostEntity;
import com.forum.microservice.notification.entity.SubforumEntity;
import com.forum.microservice.notification.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByCreator(UserEntity creator);

    List<PostEntity> findBySubforum(SubforumEntity subforum);
}
