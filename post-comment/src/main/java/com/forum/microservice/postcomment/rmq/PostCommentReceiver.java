package com.forum.microservice.postcomment.rmq;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.forum.microservice.postcomment.service.CommentService;
import com.forum.microservice.postcomment.service.SubforumService;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class PostCommentReceiver {
    private final SubforumService postService;
    private final CommentService commentService;

    public PostCommentReceiver(SubforumService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


   @RabbitListener(queues = {"q.delete-post"})
    @Transactional
    public void deletePostAndComment(ObjectNode data) {
        postService.deleteById(data.get("deletedPostId").asInt());
    }
}
