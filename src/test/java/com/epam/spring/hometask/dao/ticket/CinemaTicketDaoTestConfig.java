package com.epam.spring.hometask.dao.ticket;

import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.ticket.TicketsConfig;
import com.epam.spring.hometask.service.id.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TicketsConfig.class)
@ComponentScan(basePackages = {"com.epam.spring.hometask.service.id"})
public class CinemaTicketDaoTestConfig {

  @Autowired TicketsConfig ticketsConfig;

  @Autowired IdGeneratorService idGeneratorService;

  @Autowired
  @Qualifier("testTicketOne")
  Ticket testTicketOne;

  @Bean(name = "ticketTestDao")
  TicketDao getTicketDao(IdGeneratorService idGenerator) {
    TicketDao ticketDao = new CinemaTicketDao(idGeneratorService);
    ticketDao.save(testTicketOne);
    return ticketDao;
  }
}
