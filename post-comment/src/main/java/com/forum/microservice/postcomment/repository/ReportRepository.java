package com.forum.microservice.postcomment.repository;

import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

  List<ReportEntity> findByPost(PostEntity post);
}
