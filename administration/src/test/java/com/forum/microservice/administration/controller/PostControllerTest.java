package com.forum.microservice.administration.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.SubforumEntity;
import com.forum.microservice.administration.entity.UserEntity;
import com.forum.microservice.administration.exceptions.PostNotFoundException;
import com.forum.microservice.administration.repository.PostRepository;
import com.forum.microservice.administration.service.PostService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest {
  @MockBean private PostService postService;

  @MockBean private PostRepository postRepository;

  @Autowired private MockMvc mockMvc;

  @Test
  public void TestErrorGet() throws Exception {
    PostEntity post = new PostEntity();
    post.setId(1);
    post.setTitle("Title");
    post.setContent("Content");
    Mockito.when(postService.findById(2)).thenThrow(new PostNotFoundException("2"));
    mockMvc
        .perform(MockMvcRequestBuilders.get("http://localhost:8080/administration/posts/1"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void findAll() throws Exception {
    PostEntity post = new PostEntity();
    post.setId(1);
    post.setTitle("Title");
    post.setContent("Content");
    PostEntity post2 = new PostEntity();
    post2.setId(2);
    post2.setTitle("Title2");
    post2.setContent("Content2");
    Mockito.when(postService.findAll()).thenReturn(Arrays.asList(post, post2));
    mockMvc
        .perform(MockMvcRequestBuilders.get("http://localhost:8080/administration/posts"))
        .andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  void getPostsForUser() throws Exception {
    PostEntity post = new PostEntity();
    post.setId(1);
    post.setTitle("Title");
    post.setContent("Content");
    UserEntity user = new UserEntity();
    user.setId(1);
    user.setPosts(Arrays.asList(post));
    Mockito.when(postService.getPostsForUser(1)).thenReturn(Arrays.asList(post));
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("http://localhost:8080/administration/posts-for-user/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void getPostsOfSubforum() throws Exception {
    PostEntity post = new PostEntity();
    post.setId(1);
    post.setTitle("Title");
    post.setContent("Content");

    SubforumEntity subforum = new SubforumEntity();
    subforum.setId(1);
    subforum.setPosts(Arrays.asList(post));

    PostEntity post2 = new PostEntity();
    post2.setId(2);
    post2.setTitle("Title2");
    post2.setContent("Content2");

    SubforumEntity subforum2 = new SubforumEntity();
    subforum2.setId(2);
    subforum2.setPosts(Arrays.asList(post2));

    Mockito.when(postService.getPostsOfSubforum(subforum.getId())).thenReturn(Arrays.asList(post));
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("http://localhost:8080/administration/posts-of-subforum/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void deletePost() throws Exception {
    PostEntity post = new PostEntity();
    post.setId(1);
    post.setTitle("Title");
    post.setContent("Content");
    Mockito.when(postService.findById(1)).thenReturn((post));
    mockMvc
        .perform(MockMvcRequestBuilders.delete("http://localhost:8080/administration/posts/1"))
        .andExpect(MockMvcResultMatchers.status().is(200));
  }
}
