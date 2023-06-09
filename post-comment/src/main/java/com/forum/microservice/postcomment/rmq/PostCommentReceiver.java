package com.forum.microservice.postcomment.rmq;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.forum.microservice.postcomment.entity.CommentEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.SubforumEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import com.forum.microservice.postcomment.service.CommentService;
import com.forum.microservice.postcomment.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.SimpleTimeZone;


@Service
public class PostCommentReceiver {
    private final PostService postService;
    private final CommentService commentService;

    public PostCommentReceiver(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


   @RabbitListener(queues = {"q.delete-post"})
    @Transactional
    public void deletePostAndComment(ObjectNode data) {
        postService.deleteById(data.get("deletedPostId").asInt());
    }
}
