package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.dao.auditorium.AuditoriumDao;
import com.epam.spring.hometask.dao.auditorium.CinemaAuditoriumDao;
import com.epam.spring.hometask.dao.event.CinemaEventDao;
import com.epam.spring.hometask.dao.event.EventDao;
import com.epam.spring.hometask.dao.ticket.CinemaTicketDao;
import com.epam.spring.hometask.dao.ticket.TicketDao;
import com.epam.spring.hometask.dao.user.CinemaUserDao;
import com.epam.spring.hometask.dao.user.UserDao;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import com.epam.spring.hometask.service.id.RandomIdGeneratorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.epam.spring.hometask"})
public class DaoConfig {

  @Bean
  public IdGeneratorService getRandomIdGeneratorService() {
    return new RandomIdGeneratorService();
  }

  @Bean
  public AuditoriumDao getAuditoriumDao(IdGeneratorService idGenerator) {
    return new CinemaAuditoriumDao(idGenerator);
  }

  @Bean
  public EventDao getEventDao(IdGeneratorService idGenerator) {
    return new CinemaEventDao(idGenerator);
  }

  @Bean
  public TicketDao getTicketDao(IdGeneratorService idGenerator) {
    return new CinemaTicketDao(idGenerator);
  }

  @Bean
  public UserDao getUserDao(IdGeneratorService idGenerator) {
    return new CinemaUserDao(idGenerator);
  }
}
