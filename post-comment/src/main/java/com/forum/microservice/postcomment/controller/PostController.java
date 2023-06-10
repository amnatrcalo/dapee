package com.forum.microservice.postcomment.controller;

import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.SubforumEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import com.forum.microservice.postcomment.exceptions.PostNotFoundException;
import com.forum.microservice.postcomment.model.Post;
import com.forum.microservice.postcomment.service.PostService;
import com.forum.microservice.postcomment.service.SubforumService;
import com.forum.microservice.postcomment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-com")
public class PostController {
  private PostService postService;
  private UserService userService;
  private SubforumService subforumService;
  @Autowired
  public PostController(PostService postService, UserService userService, SubforumService subforumService) {
    this.postService = postService;
    this.userService=userService;
    this.subforumService=subforumService;
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
  }
}
