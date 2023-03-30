package com.forum.microservice.administration.controller;

import com.forum.microservice.administration.entity.ReportEntity;
import com.forum.microservice.administration.exceptions.PostNotFoundException;
import com.forum.microservice.administration.exceptions.ReportNotFoundException;
import com.forum.microservice.administration.service.ReportService;
import java.util.List;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administration")
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
  public String getNumberOfReportsOfPost(@PathVariable int postId) {

    return JSONValue.toJSONString(reportService.getNumberOfRestrictionsForPost(postId).size());
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
