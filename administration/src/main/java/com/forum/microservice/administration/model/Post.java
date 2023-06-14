package com.forum.microservice.administration.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Post {
    @NotNull
    private String title;
    @NotNull
    private String content;
    private Integer creatorId;
    private Integer subforumId;

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

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getSubforumId() {
        return subforumId;
    }

    public void setSubforumId(Integer subforumId) {
        this.subforumId = subforumId;
    }

    public Post() {
    }

    public Post(String title, String content, Integer creatorId, Integer subforumId) {
        this.title = title;
        this.content = content;
        this.creatorId = creatorId;
        this.subforumId = subforumId;
    }
}
