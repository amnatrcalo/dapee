package com.forum.microservice.administration.service;

import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.ReportEntity;
import com.forum.microservice.administration.exceptions.PostNotFoundException;
import com.forum.microservice.administration.exceptions.ReportNotFoundException;
import com.forum.microservice.administration.repository.PostRepository;
import com.forum.microservice.administration.repository.ReportRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
  private ReportRepository reportRepository;
  private PostRepository postRepository;

  @Autowired
  public ReportServiceImpl(ReportRepository reportRepository, PostRepository postRepository) {
    this.reportRepository = reportRepository;
    this.postRepository = postRepository;
  }

  @Override
  public List<ReportEntity> findAll() {
    return reportRepository.findAll();
  }

  @Override
  public ReportEntity findById(int id) {
    Optional<ReportEntity> foundEntity = reportRepository.findById(id);
    if (foundEntity.isPresent()) {
      return foundEntity.get();
    } else {
      throw new ReportNotFoundException("Report id not found: " + id);
    }
  }

  @Override
  public List<ReportEntity> getNumberOfRestrictionsForPost(int postId) {
    Optional<PostEntity> post = postRepository.findById(postId);
    if (post.isPresent()) {
      return reportRepository.findByPost(post.get());
    } else {
      throw new PostNotFoundException("Post id not found: " + postId);
    }
  }

  @Override
  public ReportEntity save(ReportEntity reportEntity) {
    return reportRepository.save(reportEntity);
  }

  @Override
  public void deleteById(int id) {
    reportRepository.deleteById(id);
  }
}
