package com.forum.microservice.administration.entity;

import jakarta.persistence.*;

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

  @OneToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "creator_id")
  private UserEntity creator;

  @OneToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "subforum_id")
  private SubforumEntity subforum;

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

  @Override
  public String toString() {
    return "PostEntity{" + "title='" + title + '\'' + ", content='" + content + '\'' + '}';
  }
}
