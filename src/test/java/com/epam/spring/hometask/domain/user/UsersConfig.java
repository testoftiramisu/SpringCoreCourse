package com.epam.spring.hometask.domain.user;

import com.epam.spring.hometask.dao.config.DataSourceConfig;
import com.epam.spring.hometask.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
public class UsersConfig {

  @Bean(name = "userOne")
  @Scope("prototype")
  public User getTestUserOne() {
    return new User("FirstName", "FirstLastName", "testUserOne@email.com");
  }

  @Bean(name = "userTwo")
  @Scope("prototype")
  public User getTestUserTwo() {
    return new User("SecondName", "SecondLastName", "testUserTwo@email.com");
  }
}
