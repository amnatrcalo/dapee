package com.forum.microservice.postcomment.repository;

import com.forum.microservice.postcomment.entity.HashtagEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<HashtagEntity, Integer> {

    List<HashtagEntity> findByPost(PostEntity post);
}
