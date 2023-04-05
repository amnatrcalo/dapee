package com.forum.microservice.administration.repository;

import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.ReportEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

  List<ReportEntity> findByPost(PostEntity post);
}
