package com.epam.spring.hometask.dao.user;

import com.epam.spring.hometask.dao.config.DataSourceConfig;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.user.UsersConfig;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({UsersConfig.class, DataSourceConfig.class})
@ComponentScan(basePackages = {"com.epam.spring.hometask.service.id"})
public class CinemaUserDaoTestConfig {

  @Autowired UsersConfig usersConfig;

  @Autowired IdGeneratorService idGeneratorService;

  @Autowired
  @Qualifier("userOne")
  User testUserOne;

  @Bean(name = "userTestDao")
  UserDao getUserDao() {
    UserDao userDao = new CinemaUserDao(idGeneratorService);
    userDao.save(testUserOne);
    return userDao;
  }
}
