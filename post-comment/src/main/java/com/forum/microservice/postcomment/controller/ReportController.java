package com.forum.microservice.postcomment.controller;

import com.forum.microservice.postcomment.entity.ReportEntity;
import com.forum.microservice.postcomment.exceptions.PostNotFoundException;
import com.forum.microservice.postcomment.exceptions.ReportNotFoundException;
import com.forum.microservice.postcomment.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-com")
public class ReportController {
  private ReportService reportService;

  @Autowired
  public ReportController(ReportService reportService) {
    this.reportService = reportService;
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
  public ReportEntity addReport(@RequestBody ReportEntity report) {
    report.setId(0);
    return reportService.save(report);
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
