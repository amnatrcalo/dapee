package com.forum.microservice.administration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "post")
public class PostEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @ManyToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "creator_id")
  private UserEntity creator;

  @ManyToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "subforum_id")
  @JsonIgnore
  private SubforumEntity subforum;

  @OneToMany(
      mappedBy = "post",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JsonIgnore
  private List<CommentEntity> comments;

  @OneToMany(
      mappedBy = "post",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JsonIgnore
  private List<ReportEntity> reports;

  public PostEntity() {}

  public PostEntity(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public UserEntity getCreator() {
    return creator;
  }

  public void setCreator(UserEntity creator) {
    this.creator = creator;
  }

  public SubforumEntity getSubforum() {
    return subforum;
  }

  public void setSubforum(SubforumEntity subforum) {
    this.subforum = subforum;
  }

  public List<CommentEntity> getComments() {
    return comments;
  }

  public void setComments(List<CommentEntity> comments) {
    this.comments = comments;
  }

  public List<ReportEntity> getReports() {
    return reports;
  }

  public void setReports(List<ReportEntity> reports) {
    this.reports = reports;
  }

  @Override
  public String toString() {
    return "PostEntity{" + "title='" + title + '\'' + ", content='" + content + '\'' + '}';
  }
}
