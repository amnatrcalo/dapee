package com.forum.microservice.administration.controller;

import com.forum.microservice.administration.entity.CommentEntity;
import com.forum.microservice.administration.exceptions.CommentNotFoundException;
import com.forum.microservice.administration.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administration")
public class CommentController {
  private CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/comments")
  public List<CommentEntity> findAll() {
    return commentService.findAll();
  }

  @GetMapping("/comments/{commentId}")
  public CommentEntity getComment(@PathVariable int commentId) {
    CommentEntity comment = commentService.findById(commentId);

    if (comment == null) {
      throw new CommentNotFoundException("Comment id not found: " + commentId);
    }

    return comment;
  }

  @PostMapping("/comments")
  public CommentEntity addComment(@RequestBody CommentEntity comment) {
    comment.setId(0);
    return commentService.save(comment);
  }

  @PutMapping("/comments")
  public CommentEntity updateComment(@RequestBody CommentEntity comment) {
    return commentService.save(comment);
  }

  @GetMapping("/comments-for-user/{userId}")
  public List<CommentEntity> getCommentsForUser(@PathVariable int userId) {
    return commentService.getCommentsForUser(userId);
  }

  @GetMapping("/comments-for-post/{postId}")
  public List<CommentEntity> getCommentsForPost(@PathVariable int postId) {
    return commentService.getCommentsForPost(postId);
  }

  @GetMapping("/comments-of-root/{rootId}")
  public List<CommentEntity> getCommentsOfRoot(@PathVariable int rootId) {
    return commentService.getByRootComment(rootId);
  }

  @DeleteMapping("/comments/{commentId}")
  public void deleteComment(@PathVariable int commentId) {
    CommentEntity tempCom = commentService.findById(commentId);

    if (tempCom == null) {
      throw new CommentNotFoundException("Comment id not found: " + commentId);
    }

    commentService.deleteById(commentId);
  }
}
