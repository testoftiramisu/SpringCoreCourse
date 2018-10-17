package com.epam.spring.hometask.service.event;

import com.epam.spring.hometask.dao.event.CinemaEventDaoTestConfig;
import com.epam.spring.hometask.dao.event.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CinemaEventDaoTestConfig.class)
public class CinemaEventServiceTestConfig {

  @Autowired
  @Qualifier("eventTestDao")
  EventDao eventDao;

  @Bean(name = "eventService")
  public EventService getEventService() {
    return new CinemaEventService(eventDao);
  }
}
