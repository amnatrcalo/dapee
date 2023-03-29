package com.forum.microservice.administration.repository;

import com.forum.microservice.administration.entity.SubforumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubforumRepository extends JpaRepository<SubforumEntity, Integer> {}
