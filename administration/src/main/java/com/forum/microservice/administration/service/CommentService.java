package com.forum.microservice.administration.service;

import com.forum.microservice.administration.entity.CommentEntity;
import java.util.List;

public interface CommentService {
  List<CommentEntity> findAll();

  CommentEntity findById(int id);

  List<CommentEntity> getCommentsForUser(int userId);

  List<CommentEntity> getCommentsForPost(int postId);

  List<CommentEntity> getByRootComment(int commentId);

  CommentEntity save(CommentEntity commentEntity);

  void deleteById(int id);
}
