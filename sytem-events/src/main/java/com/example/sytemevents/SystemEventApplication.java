package com.example.sytemevents;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;



@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SystemEventApplication {

    public static void main(String[] args) {SpringApplication.run(SystemEventApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return runner -> {
            System.out.println("....Search start app....");
        };
    }
}
