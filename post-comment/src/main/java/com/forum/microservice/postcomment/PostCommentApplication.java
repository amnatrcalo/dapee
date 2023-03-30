package com.forum.microservice.postcomment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PostCommentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostCommentApplication.class, args);
	}

}
