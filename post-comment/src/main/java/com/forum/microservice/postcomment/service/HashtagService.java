package com.forum.microservice.postcomment.service;

import com.forum.microservice.postcomment.entity.HashtagEntity;

import java.util.List;

public interface HashtagService {
    List<HashtagEntity> findAll();

    HashtagEntity findById(int id);
    List<HashtagEntity> getHashtagsForPost(int postId);
    HashtagEntity save(HashtagEntity hashtagEntity);

    void deleteById(int id);
}
