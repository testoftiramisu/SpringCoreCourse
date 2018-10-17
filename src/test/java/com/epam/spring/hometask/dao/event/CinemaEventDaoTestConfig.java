package com.epam.spring.hometask.dao.event;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.event.EventsConfig;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(EventsConfig.class)
@ComponentScan(basePackages = {"com.epam.spring.hometask.service.id"})
public class CinemaEventDaoTestConfig {

  @Autowired EventsConfig eventsConfig;

  @Autowired IdGeneratorService idGeneratorService;

  @Autowired
  @Qualifier("testEventOne")
  Event testEventOne;

  @Bean(name = "eventTestDao")
  EventDao getEventDao(IdGeneratorService idGenerator) {
    EventDao eventDao = new CinemaEventDao(idGeneratorService);
    eventDao.save(testEventOne);
    return eventDao;
  }
}
