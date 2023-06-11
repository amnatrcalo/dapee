package com.forum.microservice.search.controller;

import com.forum.microservice.search.entity.PostEntity;
import com.forum.microservice.search.entity.SubforumEntity;
import com.forum.microservice.search.entity.UserEntity;
import com.forum.microservice.search.exceptions.PostNotFoundException;
import com.forum.microservice.search.repository.PostRepository;
import com.forum.microservice.search.service.PostService;
import com.forum.microservice.search.entity.PostEntity;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest(controllers = PostController.class)
class PostTest {

    @MockBean
    private PostService postService;
    @MockBean
    private PostRepository postRepository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void findAll() throws Exception{
        PostEntity post=new PostEntity();
        post.setId(1);
        post.setTitle("Title");
        post.setContent("Content");
        PostEntity post2=new PostEntity();
        post2.setId(2);
        post2.setTitle("Title2");
        post2.setContent("Content2");
        Mockito.when(postService.findAll()).thenReturn(Arrays.asList(post,post2));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/posts"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().json("[{\"id\": 1,\"title\": \"Title\",\"content\": \"Content\"},{\"id\": 2,\"title\": \"Title2\",\"content\": \"Content2\"}]"));
    }
    @Test
    void getHashtagsForPost() throws Exception{
        PostEntity post=new PostEntity();
        post.setId(1);
        post.setTitle("Title");
        post.setContent("Content");
        SubforumEntity subforum=new SubforumEntity();
        subforum.setId(1);
        subforum.setPosts(Arrays.asList(post));
        Mockito.when(postService.getPostsOfSubforum(1)).thenReturn(Arrays.asList(post));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/posts-of-subforum/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("[{\"id\": 1,\"title\": \"Title\",\"content\": \"Content\"}]"));
    }
    @Test
    void getPostsForUser() throws Exception{
        PostEntity post=new PostEntity();
        post.setId(1);
        post.setTitle("Title");
        post.setContent("Content");
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setPosts(Arrays.asList(post));
        Mockito.when(postService.getPostsForUser(1)).thenReturn(Arrays.asList(post));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/posts-for-user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("[{\"id\": 1,\"title\": \"Title\",\"content\": \"Content\"}]"));
    }
    @Test
    void findById() throws Exception{
        PostEntity post=new PostEntity();
        post.setId(1);
        post.setTitle("Title");
        post.setContent("Content");
        Mockito.when(postService.findById(1)).thenReturn((post));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\"id\": 1,\"title\": \"Title\",\"content\": \"Content\"}"));
    }
    @Test
    public void DeletePost() throws Exception{
        PostEntity post=new PostEntity();
        post.setId(1);
        post.setTitle("Title");
        post.setContent("Content");
        Mockito.when(postService.findById(1)).thenReturn((post));
        //Mockito.when(hashtagRepository.findById(1)).thenReturn(Optional.of(hashtag));
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/search/posts/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
   @Test
    void TestErrorGet() throws Exception{
       PostEntity post=new PostEntity();
       post.setId(1);
       post.setTitle("Title");
       post.setContent("Content");
        Mockito.when(postService.findById(2)).thenThrow(new PostNotFoundException("2"));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}