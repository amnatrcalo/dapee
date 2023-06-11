package com.forum.microservice.administration.controller;

import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.ReportEntity;
import com.forum.microservice.administration.entity.UserEntity;
import com.forum.microservice.administration.exceptions.PostNotFoundException;
import com.forum.microservice.administration.exceptions.ReportNotFoundException;
import com.forum.microservice.administration.model.Report;
import com.forum.microservice.administration.service.PostService;
import com.forum.microservice.administration.service.ReportService;
import java.util.List;

import com.forum.microservice.administration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administration")
public class ReportController {
  private ReportService reportService;
  private UserService userService;
  private PostService postService;

  @Autowired
  public ReportController(ReportService reportService, UserService userService, PostService postService) {
    this.reportService = reportService;
    this.userService = userService;
    this.postService = postService;
  }
  @GetMapping("/reports")
  public List<ReportEntity> findAll() {
    return reportService.findAll();
  }

  @GetMapping("/reports/{reportId}")
  public ReportEntity getReport(@PathVariable int reportId) {
    ReportEntity report = reportService.findById(reportId);

    if (report == null) {
      throw new ReportNotFoundException("Report id not found: " + reportId);
    }

    return report;
  }

  @PostMapping("/reports")
  public ReportEntity addReport(@RequestBody Report report) {

    ReportEntity reportEntity = new ReportEntity();

    UserEntity creator = userService.findById(report.getCreatorId());
    reportEntity.setUser(creator);
    PostEntity post = postService.findById(report.getPostId());
    reportEntity.setPost(post);

    return reportService.save(reportEntity);
  }

  @PutMapping("/reports")
  public ReportEntity updateReport(@RequestBody ReportEntity report) {
    return reportService.save(report);
  }

  @GetMapping("/number-of-reports-for-post/{postId}")
  public int getNumberOfReportsOfPost(@PathVariable int postId) {
    return reportService.getNumberOfRestrictionsForPost(postId).size();
  }

  @DeleteMapping("/reports/{reportId}")
  public void deletePost(@PathVariable int reportId) {
    ReportEntity tempReport = reportService.findById(reportId);

    if (tempReport == null) {
      throw new PostNotFoundException("Report id not found: " + reportId);
    }

    reportService.deleteById(reportId);
  }
}
