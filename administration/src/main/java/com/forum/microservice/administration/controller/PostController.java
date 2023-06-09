package com.forum.microservice.administration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.exceptions.PostNotFoundException;
import com.forum.microservice.administration.service.PostService;
import java.util.List;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administration")
public class PostController {
  private PostService postService;

  private final RabbitTemplate template;

  private final Queue queue;

  private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Autowired
  public PostController(PostService postService, RabbitTemplate template, Queue queue) {
    this.postService = postService;
    this.template = template;
    this.queue = queue;
  }

  @GetMapping("/posts")
  public List<PostEntity> findAll() {

    return postService.findAll();
    // return postService.getPosts();
  }

  @GetMapping("/posts/{postId}")
  public PostEntity getPost(@PathVariable int postId) {
    PostEntity post = postService.findById(postId);
    // PostEntity post = postService.find(postId);

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
    ObjectNode data = OBJECT_MAPPER.createObjectNode();
    data.put("deletedPostId", postId);
    this.template.convertAndSend(queue.getName(), data);
  }
}
