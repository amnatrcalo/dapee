package com.forum.microservice.notification.service;

import com.forum.microservice.notification.entity.LikeEntity;
import com.forum.microservice.notification.entity.PostEntity;

import java.util.List;

public interface LikeService {
    List<LikeEntity> findAll();

    List<LikeEntity> getLikesForUser(int userId);

    List<LikeEntity> getLikesOfPost(int postId);

    LikeEntity save(LikeEntity likeEntity);

    void deleteById(int id);
}
