package com.google.demologinpage;

import com.google.demologinpage.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DemoLoginPageApplication {

    @Bean
    public User user() {
        return new User();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoLoginPageApplication.class, args);
    }

}
