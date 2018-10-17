package com.epam.spring.hometask.service.auditorium;

import com.epam.spring.hometask.dao.auditorium.AuditoriumDao;
import com.epam.spring.hometask.dao.auditorium.CinemaAuditoriumDaoTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CinemaAuditoriumDaoTestConfig.class)
public class CinemaAuditoriumServiceTestConfig {

  @Autowired
  @Qualifier("auditoriumTestDao")
  AuditoriumDao auditoriumDao;

  @Bean(name = "auditoriumService")
  public AuditoriumService getAuditoriumService() {
    return new CinemaAuditoriumService(auditoriumDao);
  }
}
