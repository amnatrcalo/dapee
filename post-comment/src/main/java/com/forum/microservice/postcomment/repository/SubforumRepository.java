package com.forum.microservice.postcomment.repository;

import com.forum.microservice.postcomment.entity.SubforumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubforumRepository extends JpaRepository<SubforumEntity, Integer> {}
