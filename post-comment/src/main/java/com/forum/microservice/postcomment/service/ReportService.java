package com.forum.microservice.postcomment.service;

import com.forum.microservice.postcomment.entity.ReportEntity;

import java.util.List;
public interface ReportService {
  List<ReportEntity> findAll();

  ReportEntity findById(int id);

  List<ReportEntity> getNumberOfRestrictionsForPost(int postId);

  ReportEntity save(ReportEntity reportEntity);

  void deleteById(int id);
}
