package com.forum.microservice.administration.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.forum.microservice.administration.entity.UserEntity;
import com.forum.microservice.administration.repository.UserRepository;
import com.forum.microservice.administration.service.UserService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = UserControllerTest.class)
class UserControllerTest {
  @MockBean private UserService userService;

  @MockBean private UserRepository userRepository;

  @Autowired private MockMvc mockMvc;

  @Test
  void findAll() throws Exception {
    UserEntity user = new UserEntity();
    user.setId(1);
    user.setEmail("test@test.com");
    user.setPassword("123989");
    user.setFirstName("test");
    user.setLastName("testtt");
    Mockito.when(userService.findAll()).thenReturn(Arrays.asList(user));
    mockMvc
        .perform(MockMvcRequestBuilders.get("http://localhost:8080/administration/users"))
        .andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  void deleteUser() throws Exception {
    UserEntity user = new UserEntity();
    user.setId(1);
    user.setEmail("test@test.com");
    user.setPassword("123989");
    user.setFirstName("test");
    user.setLastName("testtt");
    mockMvc
        .perform(MockMvcRequestBuilders.delete("http://localhost:8080/administration/users/1"))
        .andExpect(MockMvcResultMatchers.status().is(200));
  }
}
