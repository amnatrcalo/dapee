package com.forum.microservice.administration.repository;

import com.forum.microservice.administration.entity.CommentEntity;
import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
  List<CommentEntity> findByCreator(UserEntity creator);

  List<CommentEntity> findByPost(PostEntity post);

  List<CommentEntity> findByRootComment(CommentEntity comment);
}
