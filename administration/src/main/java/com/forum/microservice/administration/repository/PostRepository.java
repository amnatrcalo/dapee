package com.forum.microservice.administration.repository;

import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.SubforumEntity;
import com.forum.microservice.administration.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
  List<PostEntity> findByCreator(UserEntity creator);

  List<PostEntity> findBySubforum(SubforumEntity subforum);
}
