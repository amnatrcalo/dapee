package com.forum.microservice.postcomment.repository;

import com.forum.microservice.postcomment.entity.CommentEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
  List<CommentEntity> findByCreator(UserEntity creator);

  List<CommentEntity> findByPost(PostEntity post);

  List<CommentEntity> findByRootComment(CommentEntity comment);
}
