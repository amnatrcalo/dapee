package com.forum.microservice.administration.controller;

import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.exceptions.PostNotFoundException;
import com.forum.microservice.administration.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administration")
public class PostController {
  private PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/posts")
  public List<PostEntity> findAll() {
    return postService.findAll();
  }

  @GetMapping("/posts/{postId}")
  public PostEntity getPost(@PathVariable int postId) {
    PostEntity post = postService.findById(postId);

    if (post == null) {
      throw new PostNotFoundException("Post id not found: " + postId);
    }

    return post;
  }

  @PostMapping("/posts")
  public PostEntity addPost(@RequestBody PostEntity post) {
    post.setId(0);
    return postService.save(post);
  }

  @PutMapping("/posts")
  public PostEntity updatePost(@RequestBody PostEntity post) {
    return postService.save(post);
  }

  @GetMapping("/posts-for-user/{userId}")
  public List<PostEntity> getPostsForUser(@PathVariable int userId) {
    return postService.getPostsForUser(userId);
  }

  @GetMapping("posts-of-subforum/{subforumId}")
  public List<PostEntity> getPostsOfSubforum(@PathVariable int subforumId) {
    return postService.getPostsOfSubforum(subforumId);
  }

  @DeleteMapping("/posts/{postId}")
  public void deletePost(@PathVariable int postId) {
    PostEntity tempPost = postService.findById(postId);

    if (tempPost == null) {
      throw new PostNotFoundException("Post id not found: " + postId);
    }

    postService.deleteById(postId);
  }
}
