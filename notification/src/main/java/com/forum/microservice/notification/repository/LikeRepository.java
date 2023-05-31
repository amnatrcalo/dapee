package com.forum.microservice.notification.repository;

import com.forum.microservice.notification.entity.LikeEntity;
import com.forum.microservice.notification.entity.PostEntity;
import com.forum.microservice.notification.entity.SubforumEntity;
import com.forum.microservice.notification.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository  extends JpaRepository<LikeEntity, Integer> {
    List<LikeEntity> findByPost(PostEntity post);
    List<LikeEntity> findByUser(UserEntity user);
}
