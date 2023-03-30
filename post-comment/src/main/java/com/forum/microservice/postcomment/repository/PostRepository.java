package com.forum.microservice.postcomment.repository;

import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.SubforumEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
  List<PostEntity> findByCreator(UserEntity creator);

  List<PostEntity> findBySubforum(SubforumEntity subforum);
}
