package com.forum.microservice.postcomment.service;

import com.forum.microservice.postcomment.entity.LikeEntity;

import java.util.List;

public interface LikeService {
    List<LikeEntity> findAll();

    LikeEntity findById(int id);
    List<LikeEntity> getLikesForUser(int userId);
    List<LikeEntity> getLikesForPost(int postId);
    List<LikeEntity> getLikesForComment(int commentId);

    LikeEntity save(LikeEntity likeEntity);

    void deleteById(int id);
}
