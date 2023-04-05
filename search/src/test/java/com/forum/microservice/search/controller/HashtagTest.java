package com.forum.microservice.search.controller;

import com.forum.microservice.search.entity.PostEntity;
import com.forum.microservice.search.exceptions.HashtagNotFoundException;
import com.forum.microservice.search.repository.HashtagRepository;
import com.forum.microservice.search.service.HashtagService;
import com.forum.microservice.search.entity.HashtagEntity;
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

@WebMvcTest(controllers = HashtagController.class)
class HashtagTest {

    @MockBean
    private HashtagService hashtagService;
    @MockBean
    private HashtagRepository hashtagRepository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void findAll() throws Exception{
        HashtagEntity hashtag=new HashtagEntity();
        hashtag.setTitle("#dinner");
        hashtag.setId(1);
        HashtagEntity hashtag2=new HashtagEntity();
        hashtag2.setId(2);
        hashtag2.setTitle("#tennis");
        Mockito.when(hashtagService.findAll()).thenReturn(Arrays.asList(hashtag,hashtag2));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/hashtags"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().json("[{\"id\": 1,\"title\": \"#dinner\"},{\"id\": 2,\"title\": \"#tennis\"}]"));
    }
    @Test
    void getHashtagsForPost() throws Exception{
        HashtagEntity hashtag=new HashtagEntity();
        hashtag.setTitle("#tennis");
        hashtag.setId(1);
        PostEntity post=new PostEntity();
        post.setId(1);
        post.setHashtags(Arrays.asList(hashtag));
        Mockito.when(hashtagService.getHashtagsForPost(1)).thenReturn(Arrays.asList(hashtag));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/hashtags-for-post/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("[{\"id\": 1,\"title\": \"#tennis\"}]"));
    }

    @Test
    void findById() throws Exception{
        HashtagEntity hashtag=new HashtagEntity();
        hashtag.setTitle("#tennis");
        hashtag.setId(1);
        Mockito.when(hashtagService.findById(1)).thenReturn((hashtag));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/hashtags/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\"id\": 1,\"title\": \"#tennis\"}"));
    }
    @Test
    public void DeleteHashtag() throws Exception{
        HashtagEntity hashtag=new HashtagEntity();
        hashtag.setTitle("#tennis");
        hashtag.setId(1);
        Mockito.when(hashtagService.findById(1)).thenReturn((hashtag));
        //Mockito.when(hashtagRepository.findById(1)).thenReturn(Optional.of(hashtag));
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/search/hashtags/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    void TestErrorGet() throws Exception{
        HashtagEntity hashtag=new HashtagEntity();
        hashtag.setTitle("#tennis");
        hashtag.setId(1);
        Mockito.when(hashtagService.findById(2)).thenThrow(new HashtagNotFoundException("2"));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/hashtags/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}