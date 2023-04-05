package com.forum.microservice.postcomment.service;


import com.forum.microservice.postcomment.entity.PostEntity;

import java.util.List;

public interface PostService {
  List<PostEntity> findAll();

  PostEntity findById(int id);

  List<PostEntity> getPostsForUser(int userId);

  List<PostEntity> getPostsOfSubforum(int subforumId);

  PostEntity save(PostEntity postEntity);

  void deleteById(int id);
}
