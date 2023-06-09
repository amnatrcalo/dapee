package com.forum.microservice.administration.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

 @Bean
  public Queue deletePostCreationQueue() {
    return new Queue("q.delete-post");
  }
}
