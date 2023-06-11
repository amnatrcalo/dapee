package com.forum.microservice.postcomment.controller;



import com.forum.microservice.postcomment.entity.CommentEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import com.forum.microservice.postcomment.exceptions.CommentNotFoundException;
import com.forum.microservice.postcomment.model.Comment;
import com.forum.microservice.postcomment.service.CommentService;
import com.forum.microservice.postcomment.service.PostService;
import com.forum.microservice.postcomment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-com")
public class CommentController {
  private CommentService commentService;
  private UserService userService;
  private PostService postService;

  @Autowired
  public CommentController(CommentService commentService, UserService userService, PostService postService) {
    this.commentService = commentService;
    this.userService = userService;
    this.postService = postService;
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
  public CommentEntity addComment(@RequestBody Comment comment) {
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setText(comment.getText());
    UserEntity user = userService.findById(comment.getCreatorId());
    PostEntity post = postService.findById(comment.getPostId());
    commentEntity.setPost(post);
    commentEntity.setCreator(user);

    return commentService.save(commentEntity);
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
