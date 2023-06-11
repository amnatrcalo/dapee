package com.forum.microservice.notification.model;

public class Notification {

    private String text;

    private Integer receiverId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Notification() {}

    public Notification(String text, Integer receiverId) {
        this.text = text;
        this.receiverId = receiverId;
    }
}
