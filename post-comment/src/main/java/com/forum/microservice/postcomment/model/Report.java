package com.forum.microservice.postcomment.model;

public class Report {

    private Integer creatorId;
    private Integer postId;

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Report(Integer creatorId, Integer postId) {
        this.creatorId = creatorId;
        this.postId = postId;
    }

    public Report() {
    }
}
