package com.forum.microservice.notification.repository;

import com.forum.microservice.notification.entity.SubforumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubforumRepository extends JpaRepository<SubforumEntity, Integer> {

  @Query("SELECT s FROM SubforumEntity s WHERE s.name = ?1")
  SubforumEntity getSubforumByName(String name);
}
