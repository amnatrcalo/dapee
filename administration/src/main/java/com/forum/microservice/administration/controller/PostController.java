package com.forum.microservice.administration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.SubforumEntity;
import com.forum.microservice.administration.entity.UserEntity;
import com.forum.microservice.administration.exceptions.PostNotFoundException;
import com.forum.microservice.administration.model.Post;
import com.forum.microservice.administration.service.PostService;
import java.util.List;

import com.forum.microservice.administration.service.SubforumService;
import com.forum.microservice.administration.service.UserService;
import org.apache.catalina.User;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administration")
public class PostController {
  private PostService postService;
  private UserService userService;
  private SubforumService subforumService;

  private final RabbitTemplate template;

  private final Queue queue;

  private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Autowired
  public PostController(PostService postService, UserService userService, SubforumService subforumService, RabbitTemplate template, Queue queue) {
    this.postService = postService;
    this.userService=userService;
    this.subforumService=subforumService;
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
  public PostEntity addPost(@RequestBody Post post) {
   PostEntity postEntity = new PostEntity();
   postEntity.setTitle(post.getTitle());
   postEntity.setContent(post.getContent());
   UserEntity creator = userService.findById(post.getCreatorId());
   postEntity.setCreator(creator);
    SubforumEntity subforum = subforumService.findById(post.getSubforumId());
    postEntity.setSubforum(subforum);

   return postService.save(postEntity);
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
