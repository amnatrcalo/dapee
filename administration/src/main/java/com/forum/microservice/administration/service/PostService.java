package com.forum.microservice.administration.service;

import com.forum.microservice.administration.entity.PostEntity;
import java.util.List;

public interface PostService {
  List<PostEntity> findAll();

  PostEntity findById(int id);

  List<PostEntity> getPostsForUser(int userId);

  List<PostEntity> getPostsOfSubforum(int subforumId);

  PostEntity save(PostEntity postEntity);

  void deleteById(int id);

  List<PostEntity> getPosts();

  PostEntity find(int id);

  void softDelete(PostEntity post);
}
