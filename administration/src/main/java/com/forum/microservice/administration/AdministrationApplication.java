package com.forum.microservice.administration;

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
  public CommandLineRunner commandLineRunner() {
    return runner -> {
      System.out.println("....Administration start app....");
    };
  }
}
