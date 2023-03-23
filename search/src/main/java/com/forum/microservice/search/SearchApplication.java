package com.forum.microservice.search;

import com.forum.microservice.search.dao.PostDAO;
import com.forum.microservice.search.dao.UserDAO;
import com.forum.microservice.search.entity.HashtagEntity;
import com.forum.microservice.search.entity.PostEntity;
import com.forum.microservice.search.entity.SubforumEntity;
import com.forum.microservice.search.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserDAO userDao, PostDAO postDAO) {
		return runner -> {
			System.out.println("....Search start app....");
			// createUser(userDao);
			// createPost(postDAO);
			testAll(userDao);
		};
	}

	private void testAll(UserDAO userDao) {
		UserEntity userOne = new UserEntity("Test", "Test", "test@test.com", "test");
		UserEntity userTwo = new UserEntity("Test2", "Test2", "test2@test.com", "test2");

		PostEntity postOne = new PostEntity("Test one title", "Test one content");
		PostEntity postTwo = new PostEntity("Test two title", "Test two content");
		PostEntity postThree = new PostEntity("Test three title", "Test three content");

		postOne.setCreator(userOne);
		postTwo.setCreator(userOne);
		postThree.setCreator(userTwo);

		List<PostEntity> postsForUserOne = new ArrayList<>();
		postsForUserOne.add(postOne);
		postsForUserOne.add(postTwo);
		userOne.setPosts(postsForUserOne);
		List<PostEntity> postsForUserTwo = new ArrayList<>();
		postsForUserTwo.add(postThree);
		userTwo.setPosts(postsForUserTwo);

		HashtagEntity hashtagForPostOne = new HashtagEntity("Great");
		hashtagForPostOne.setPost(postOne);
		List<HashtagEntity> hashtagsOne = new ArrayList<>();
		hashtagsOne.add(hashtagForPostOne);
		postOne.setHashtags(hashtagsOne);

		SubforumEntity subforumOneAndTwo = new SubforumEntity("Football");
		subforumOneAndTwo.setPosts(postsForUserOne);
		postOne.setSubforum(subforumOneAndTwo);
		postTwo.setSubforum(subforumOneAndTwo);
		postThree.setSubforum(new SubforumEntity("Tennis"));

		userDao.save(userOne);
		userDao.save(userTwo);
	}

	private void createPost(PostDAO postDAO) {
		System.out.println("Creating new post...");
		PostEntity postEntity = new PostEntity("MY title", "This is content");
		UserEntity userEntity = new UserEntity("Amna", "Trcalo", "amna@amna.com", "amna");
		SubforumEntity subforumEntity = new SubforumEntity("My subforum");
		postEntity.setCreator(userEntity);
		postEntity.setSubforum(subforumEntity);
		postDAO.save(postEntity);
	}

	private void createUser(UserDAO userDao) {
		System.out.println("Creating new user...");
		UserEntity userEntity = new UserEntity("Mujo", "Mujic", "mujo@mujo.ba", "12345");
		userDao.save(userEntity);
	}
}
