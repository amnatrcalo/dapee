package com.forum.microservice.administration;

import com.forum.microservice.administration.dao.UserDao;
import com.forum.microservice.administration.entity.UserEntity;
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
  public CommandLineRunner commandLineRunner(UserDao userDao) {
    return runner -> {
      System.out.println("....Administration start app....");
      createUser(userDao);
    };
  }

  private void createUser(UserDao userDao) {
    System.out.println("Creating new user");
    UserEntity userEntity = new UserEntity("Mujo", "Mujic", "mujo@mujo.ba", "12345");
    userDao.save(userEntity);
  }
}
