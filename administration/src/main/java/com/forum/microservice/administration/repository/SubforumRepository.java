package com.forum.microservice.administration.repository;

import com.forum.microservice.administration.entity.SubforumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubforumRepository extends JpaRepository<SubforumEntity, Integer> {

  @Query("SELECT s FROM SubforumEntity s WHERE s.name = ?1")
  SubforumEntity getSubforumByName(String name);
}
