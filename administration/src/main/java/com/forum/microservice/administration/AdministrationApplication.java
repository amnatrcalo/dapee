package com.forum.microservice.administration;

import com.forum.microservice.administration.dao.PostDAO;
import com.forum.microservice.administration.dao.UserDao;
import com.forum.microservice.administration.entity.CommentEntity;
import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.SubforumEntity;
import com.forum.microservice.administration.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdministrationApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdministrationApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(UserDao userDao, PostDAO postDAO) {
    return runner -> {
      System.out.println("....Administration start app....");
      // createUser(userDao);
      // createPost(postDAO);
      testAll(userDao);
    };
  }

  private void testAll(UserDao userDao) {
    System.out.println("Testing Admin...");
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

    List<CommentEntity> commentsForPostOne = new ArrayList<>();
    CommentEntity commentForPostOne = new CommentEntity("Great post");
    CommentEntity commentForPostTwo = new CommentEntity("Bad post");
    CommentEntity replyTo = new CommentEntity("I agree");
    commentsForPostOne.add(commentForPostOne);
    commentsForPostOne.add(commentForPostTwo);
    postOne.setComments(commentsForPostOne);

    commentForPostOne.setCreator(userOne);
    commentForPostOne.setPost(postOne);
    commentForPostTwo.setPost(postTwo);
    commentForPostTwo.setCreator(userTwo);
    replyTo.setPost(postOne);
    replyTo.setCreator(userTwo);
    replyTo.setRootComment(commentForPostOne);

    SubforumEntity subforumOneAndTwo = new SubforumEntity("Football");
    subforumOneAndTwo.setPosts(postsForUserOne);
    postOne.setSubforum(subforumOneAndTwo);
    postTwo.setSubforum(subforumOneAndTwo);
    postThree.setSubforum(new SubforumEntity("Tennis"));

    List<UserEntity> admins = new ArrayList<>();
    admins.add(userOne);
    subforumOneAndTwo.setAdmins(admins);

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

  private void createUser(UserDao userDao) {
    System.out.println("Creating new user...");
    UserEntity userEntity = new UserEntity("Mujo", "Mujic", "mujo@mujo.ba", "12345");
    userDao.save(userEntity);
  }
}
