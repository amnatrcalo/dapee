package com.forum.microservice.search.controller;

import com.forum.microservice.search.entity.HashtagEntity;
import com.forum.microservice.search.entity.PostEntity;
import com.forum.microservice.search.entity.SubforumEntity;
import com.forum.microservice.search.exceptions.HashtagNotFoundException;
import com.forum.microservice.search.exceptions.SubforumNotFoundException;
import com.forum.microservice.search.repository.PostRepository;
import com.forum.microservice.search.repository.SubforumRepository;
import com.forum.microservice.search.service.PostService;
import com.forum.microservice.search.service.SubforumService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = SubforumController.class)
class SubforumTest {

    @MockBean
    private SubforumService subforumService;
    @MockBean
    private SubforumRepository subforumRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAll() throws Exception{
        SubforumEntity subforum=new SubforumEntity();
        subforum.setId(1);
        subforum.setName("Title");
        SubforumEntity subforum2=new SubforumEntity();
        subforum2.setId(2);
        subforum2.setName("Title2");
        Mockito.when(subforumService.findAll()).thenReturn(Arrays.asList(subforum,subforum2));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/subforums"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(content().json("[{\"id\": 1,\"name\": \"Title\"},{\"id\": 2,\"name\": \"Title2\"}]"));
    }

    @Test
    void findById() throws Exception{
        SubforumEntity subforum=new SubforumEntity();
        subforum.setId(1);
        subforum.setName("Title");
        Mockito.when(subforumService.findById(1)).thenReturn((subforum));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/subforums/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\"id\": 1,\"name\": \"Title\"}"));
    }
    @Test
    public void DeleteHashtag() throws Exception{
        SubforumEntity subforum=new SubforumEntity();
        subforum.setId(1);
        subforum.setName("Title");
        Mockito.when(subforumService.findById(1)).thenReturn((subforum));
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/search/subforums/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    void TestErrorGet() throws Exception{
        SubforumEntity subforum=new SubforumEntity();
        subforum.setId(1);
        subforum.setName("Title");
        Mockito.when(subforumService.findById(2)).thenThrow(new SubforumNotFoundException("2"));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/search/subforums/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
