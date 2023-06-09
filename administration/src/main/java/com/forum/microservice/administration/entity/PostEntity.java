package com.forum.microservice.administration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "post")
public class PostEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title")
  @NotNull
  @NotBlank(message = "Title is mandatory")
  private String title;

  @Column(name = "content")
  @NotNull
  @NotBlank(message = "Content is mandatory")
  @Size(message = "Content shouldn't contain more than 100 characters", max = 100)
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
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
  @JsonIgnore
  private List<CommentEntity> comments;

  @Column(name = "deleted")
  private Boolean deleted = false;

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

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public String toString() {
    return "PostEntity{" + "title='" + title + '\'' + ", content='" + content + '\'' + '}';
  }
}
