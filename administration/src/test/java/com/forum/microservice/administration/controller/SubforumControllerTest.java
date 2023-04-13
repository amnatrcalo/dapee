package com.forum.microservice.administration.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.forum.microservice.administration.entity.SubforumEntity;
import com.forum.microservice.administration.repository.SubforumRepository;
import com.forum.microservice.administration.service.SubforumService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = SubforumController.class)
class SubforumControllerTest {
  @MockBean private SubforumService subforumService;

  @MockBean private SubforumRepository subforumRepository;

  @Autowired private MockMvc mockMvc;

  @Test
  void getSubforumByName() throws Exception {
    SubforumEntity subforum = new SubforumEntity();
    subforum.setId(1);
    subforum.setName("Football");

    Mockito.when(subforumService.getSubforumByName("Football")).thenReturn(subforum);
    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                "http://localhost:8080/administration/subforums/name/Football"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
