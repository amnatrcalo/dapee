package com.forum.microservice.postcomment.model;

public class Like {
    private Integer voterId;
    private Integer postId;

    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Like(Integer voterId, Integer postId) {
        this.voterId = voterId;
        this.postId = postId;
    }

    public Like() {
    }
}
