package com.epam.spring.hometask.service.user;

import com.epam.spring.hometask.dao.user.CinemaUserDaoTestConfig;
import com.epam.spring.hometask.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CinemaUserDaoTestConfig.class)
public class CinemaUserServiceTestConfig {

  @Autowired
  @Qualifier("userTestDao")
  UserDao userDao;

  @Bean(name = "userService")
  public UserService getUserService() {
    return new CinemaUserService(userDao);
  }
}
