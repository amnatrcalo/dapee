package com.forum.microservice.administration.repository;

import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.SubforumEntity;
import com.forum.microservice.administration.entity.UserEntity;
import io.micrometer.common.lang.NonNull;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
  List<PostEntity> findByCreator(UserEntity creator);

  List<PostEntity> findBySubforum(SubforumEntity subforum);

  @Query(value = "SELECT * FROM Post p where p.deleted = FALSE", nativeQuery = true)
  List<PostEntity> getPosts();

  @Query(value = "SELECT * from Post p where p.id = :id  AND p.deleted = FALSE", nativeQuery = true)
  Optional<PostEntity> find(@NonNull Integer id);
}
