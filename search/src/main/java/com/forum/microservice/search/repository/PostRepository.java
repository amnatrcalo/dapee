package com.forum.microservice.search.repository;

import com.forum.microservice.search.entity.PostEntity;
import com.forum.microservice.search.entity.SubforumEntity;
import com.forum.microservice.search.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByCreator(UserEntity creator);

    List<PostEntity> findBySubforum(SubforumEntity subforum);
}
