package com.forum.microservice.postcomment.model;

public class Comment {
    private String text;

    private Integer postId;
    private Integer creatorId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Comment(String text, Integer postId, Integer creatorId) {
        this.text = text;
        this.postId = postId;
        this.creatorId = creatorId;
    }

    public Comment() {
    }
}
