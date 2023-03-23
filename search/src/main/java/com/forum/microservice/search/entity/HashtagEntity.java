package com.forum.microservice.search.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "hashtag")
public class HashtagEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    private PostEntity post;

    ////////////////////////////
    public HashtagEntity() {
    }

    public HashtagEntity(String title) {
        this.title = title;
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

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
/////////
    @Override
    public String toString() {
    return "HashtagEntity{" + "title='" + title + '\'' + '}';
}
}
