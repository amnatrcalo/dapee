package com.forum.microservice.search.service;

import com.forum.microservice.search.entity.PostEntity;
import java.util.List;

public interface PostService {
    List<PostEntity> findAll();

    PostEntity findById(int id);

    List<PostEntity> getPostsForUser(int userId);

    List<PostEntity> getPostsOfSubforum(int subforumId);

    PostEntity save(PostEntity postEntity);

    void deleteById(int id);
}
