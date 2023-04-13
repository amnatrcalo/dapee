package com.forum.microservice.search.repository;

import com.forum.microservice.search.entity.SubforumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubforumRepository extends JpaRepository<SubforumEntity, Integer> {}
