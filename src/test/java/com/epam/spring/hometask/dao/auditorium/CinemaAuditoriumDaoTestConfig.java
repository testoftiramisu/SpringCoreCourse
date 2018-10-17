package com.epam.spring.hometask.dao.auditorium;

import com.epam.spring.hometask.domain.auditorium.AuditoriumsConfig;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collections;

@Configuration
@Import(AuditoriumsConfig.class)
@ComponentScan(basePackages = {"com.epam.spring.hometask.service.id"})
public class CinemaAuditoriumDaoTestConfig {

  @Autowired AuditoriumsConfig auditoriumsConfig;

  @Bean(name = "auditoriumTestDao")
  AuditoriumDao getAuditoriumDao(IdGeneratorService idGenerator) {
    AuditoriumDao auditoriumDao = new CinemaAuditoriumDao(idGenerator);
    auditoriumDao.setAuditoriums(Collections.singletonList(auditoriumsConfig.getImax()));
    return auditoriumDao;
  }
}
