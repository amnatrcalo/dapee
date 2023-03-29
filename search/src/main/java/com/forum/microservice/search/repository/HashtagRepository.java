package com.forum.microservice.search.repository;
import com.forum.microservice.search.entity.HashtagEntity;
import com.forum.microservice.search.entity.PostEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HashtagRepository extends JpaRepository<HashtagEntity, Integer>{
    List<HashtagEntity> findByPost(PostEntity post);
}
