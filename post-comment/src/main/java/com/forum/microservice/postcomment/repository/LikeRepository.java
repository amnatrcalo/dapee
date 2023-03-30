package com.forum.microservice.postcomment.repository;

import com.forum.microservice.postcomment.entity.CommentEntity;
import com.forum.microservice.postcomment.entity.LikeEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    List<LikeEntity> findByVoter(UserEntity creator);

    List<LikeEntity> findByPost(PostEntity post);
    List<LikeEntity> findByComment(CommentEntity comment);

}
