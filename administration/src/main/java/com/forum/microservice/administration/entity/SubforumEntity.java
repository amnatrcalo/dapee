package com.forum.microservice.administration.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "subforum")
public class SubforumEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @OneToMany(
      mappedBy = "subforum",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<PostEntity> posts;

  @ManyToMany(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(
      name = "subforum-admin",
      joinColumns = @JoinColumn(name = "subforum_id"),
      inverseJoinColumns = @JoinColumn(name = "admin_id"))
  private List<UserEntity> admins;

  public SubforumEntity() {}

  public SubforumEntity(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<PostEntity> getPosts() {
    return posts;
  }

  public void setPosts(List<PostEntity> posts) {
    this.posts = posts;
  }

  public List<UserEntity> getAdmins() {
    return admins;
  }

  public void setAdmins(List<UserEntity> admins) {
    this.admins = admins;
  }

  @Override
  public String toString() {
    return "SubforumEntity{" + "name='" + name + '\'' + '}';
  }
}
