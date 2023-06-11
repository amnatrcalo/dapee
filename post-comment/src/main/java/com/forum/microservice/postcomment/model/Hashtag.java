package com.forum.microservice.postcomment.model;

public class Hashtag {

    private String name;
    private Integer postId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Hashtag(String name, Integer postId) {
        this.name = name;
        this.postId = postId;
    }

    public Hashtag() {
    }
}
