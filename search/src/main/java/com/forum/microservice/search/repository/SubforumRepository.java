package com.forum.microservice.search.repository;

import com.forum.microservice.search.entity.SubforumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubforumRepository extends JpaRepository<SubforumEntity, Integer> {
    @Query("SELECT s FROM SubforumEntity s WHERE s.name LIKE %?1%")
    SubforumEntity getSubforumContainsSubstring(String name);
}
